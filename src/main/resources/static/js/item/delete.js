document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".delete-btn").forEach(btn => {
        btn.addEventListener("click", async () => {
            const itemId = btn.getAttribute("data-id");

            if (!confirm("정말 삭제하시겠습니까?")) return;

            const res = await fetch(`/item/items/${itemId}`, {
                method: "DELETE"
            });

            if (res.ok) {
                btn.closest(".detail").remove();
            } else {
                alert("");
            }
        });
    });
});
