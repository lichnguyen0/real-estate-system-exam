document.addEventListener("DOMContentLoaded", function () {
    const btnDelete = document.getElementById("btn-delete");
    const modalBackdrop = document.getElementById("confirm-modal");
    const btnCancelModal = document.getElementById("btn-cancel-modal");

    if (btnDelete && modalBackdrop) {
        // Show modal when clicking Xóa
        btnDelete.addEventListener("click", function (e) {
            e.preventDefault();
            modalBackdrop.classList.add("show");
        });
    }

    if (btnCancelModal) {
        // If clicking "Không" (No) in modal, redirect back to list view
        btnCancelModal.addEventListener("click", function (e) {
            e.preventDefault();
            window.location.href = "/giao-dich";
        });
    }

    // Optional: close modal when clicking on backdrop itself
    if (modalBackdrop) {
        modalBackdrop.addEventListener("click", function (e) {
            if (e.target === modalBackdrop) {
                window.location.href = "/giao-dich";
            }
        });
    }
});
