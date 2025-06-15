async function getURL(e) {
    const file = e.files[0];
    if (!file) return;

    const name = encodeURIComponent(file.name);
    let result = await fetch("/item/presigned-url?filename=" + name);
    result = await result.text();

    let res = await fetch(result, {
        method: "PUT",
        body: file
    });

    if (res.ok) {
        const url = result.split("?")[0];
        const img = document.getElementById("preview");
        const text = document.querySelector(".upload-text");

        img.src = url;

        // ✅ 이미지 보일 때 텍스트는 숨기기
        text.style.display = "none";
        document.getElementById("imgUrl").value = url;
    } else {
        alert("이미지 업로드에 실패했습니다.");
    }
}



