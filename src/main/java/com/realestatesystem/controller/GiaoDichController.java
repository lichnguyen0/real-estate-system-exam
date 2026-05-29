package com.realestatesystem.controller;

import com.realestatesystem.dto.GiaoDichDTO;
import com.realestatesystem.model.GiaoDich;
import com.realestatesystem.model.KhachHang;
import com.realestatesystem.repository.KhachHangRepository;
import com.realestatesystem.service.GiaoDichService;
import com.realestatesystem.validator.GiaoDichValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Controller
@RequestMapping("/giao-dich")
public class GiaoDichController {

    private final GiaoDichService service;
    private final KhachHangRepository khachHangRepository;

    public GiaoDichController(GiaoDichService service, KhachHangRepository khachHangRepository) {
        this.service = service;
        this.khachHangRepository = khachHangRepository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("list", service.findAll());
        return "giao-dich/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id, Model model) {
        model.addAttribute("gd", service.findById(id));
        return "giao-dich/detail";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        service.delete(id);
        return "redirect:/giao-dich";
    }

    @GetMapping("/search")
    public String search(@RequestParam(required = false) String ten,
                         @RequestParam(required = false) String loai,
                         Model model) {
        System.out.println("[DEBUG SEARCH] Received params -> ten: '" + ten + "', loai: '" + loai + "'");
        List<GiaoDich> result = service.search(ten, loai);
        System.out.println("[DEBUG SEARCH] Query returned " + (result != null ? result.size() : 0) + " records.");
        
        model.addAttribute("list", result);
        model.addAttribute("ten", ten);
        model.addAttribute("loai", loai);

        return "giao-dich/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("dto", new GiaoDichDTO());
        model.addAttribute("customers", khachHangRepository.findAll());
        return "giao-dich/form";
    }

    @PostMapping("/add")
    public String addSubmit(@ModelAttribute("dto") GiaoDichDTO dto, Model model) {
        // Validation check
        String errorMsg = GiaoDichValidator.validate(dto);
        if (errorMsg != null) {
            model.addAttribute("error", errorMsg);
            model.addAttribute("customers", khachHangRepository.findAll());
            return "giao-dich/form";
        }

        // Validate ID unique
        if (service.findById(dto.getMaGiaoDich()) != null) {
            model.addAttribute("error", "Mã giao dịch đã tồn tại trên hệ thống!");
            model.addAttribute("customers", khachHangRepository.findAll());
            return "giao-dich/form";
        }

        // Date check
        LocalDate parsedDate;
        try {
            if (dto.getNgayGiaoDich() == null || dto.getNgayGiaoDich().isEmpty()) {
                model.addAttribute("error", "Ngày giao dịch bắt buộc nhập");
                model.addAttribute("customers", khachHangRepository.findAll());
                return "giao-dich/form";
            }
            parsedDate = LocalDate.parse(dto.getNgayGiaoDich());
            if (!parsedDate.isAfter(LocalDate.now())) {
                model.addAttribute("error", "Ngày giao dịch phải lớn hơn ngày hiện tại!");
                model.addAttribute("customers", khachHangRepository.findAll());
                return "giao-dich/form";
            }
        } catch (DateTimeParseException e) {
            model.addAttribute("error", "Ngày giao dịch không đúng định dạng YYYY-MM-DD");
            model.addAttribute("customers", khachHangRepository.findAll());
            return "giao-dich/form";
        }

        // Convert DTO to Entity
        GiaoDich gd = new GiaoDich();
        gd.setMaGiaoDich(dto.getMaGiaoDich());
        gd.setLoaiDichVu(dto.getLoaiDichVu());
        gd.setDonGia(dto.getDonGia());
        gd.setDienTich(dto.getDienTich());
        gd.setNgayGiaoDich(parsedDate);

        KhachHang kh = khachHangRepository.findById(dto.getMaKhachHang()).orElse(null);
        if (kh == null) {
            model.addAttribute("error", "Vui lòng chọn khách hàng hợp lệ!");
            model.addAttribute("customers", khachHangRepository.findAll());
            return "giao-dich/form";
        }
        gd.setKhachHang(kh);

        // Save
        service.save(gd);

        return "redirect:/giao-dich?success=true";
    }
}
