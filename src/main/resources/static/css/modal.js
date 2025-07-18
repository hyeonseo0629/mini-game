const insertDialog = document.getElementById('insertModal');
const detailDialog = document.getElementById('detailModal');
const openAddBtn = document.getElementById('openInsertBtn');
const openDetailBtn = document.getElementById('openDetailBtn');
const closeBtn = document.getElementById('closeBtn');
const closeDetailBtn = document.getElementById('closeDetailBtn');
const editBtn = document.getElementById('editBtn');
const deleteBtn = document.getElementById('deleteBtn');

const titleEl = document.querySelector("#detailTitle")
const contentEl = document.querySelector("#detailContent")
const nickNameEl = document.querySelector("#detailNickname")
const dateEl = document.querySelector("#detailDate")
openAddBtn.addEventListener('click', () => insertDialog.showModal());
closeBtn.addEventListener('click', () => insertDialog.close());
openDetailBtn.addEventListener('click', () => detailDialog.showModal());
closeDetailBtn.addEventListener('click', () => detailDialog.close());


document.querySelector("#addBtn").addEventListener("click", (e) => {
    let type = e.target.dataset.type;
    const text_title = document.querySelector("input[name='text_title']").value;
    const text_content = document.querySelector("textarea[name='text_content']").value;
    console.log(text_title);
    console.log(text_content);
    const obj = {text_title, text_content, "text_type": type};
    const url = `/text/add/${type}`;
    fetch(url, {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(obj)  // 여기!
    }).then(res => res.json()).then(data => {
        console.log(data); // 1, 0;
        location.href = "/text/" + type.toLowerCase();
    });

});

document.querySelectorAll(".text-row").forEach((el) => {
    el.addEventListener("click", e => {
        console.log(el);
        console.log(el.dataset);
        openDetailBtn.click();
        titleEl.value = el.dataset.title;
        contentEl.innerText = el.dataset.content;
        nickNameEl.value = el.dataset.nickname;
        dateEl.innerText = el.dataset.date;
    })
})

editBtn.addEventListener('click', e => {
    titleEl.disabled = !titleEl.disabled;
    contentEl.disabled = !contentEl.disabled;
})

deleteBtn.addEventListener('click', e => {
    const deleteBtn = document.getElementById("deleteBtn");

    deleteBtn.addEventListener('click', e => {
        const confirmed = confirm("정말 삭제하시겠습니까?");
        if (!confirmed) return;

        const id = deleteBtn.dataset.id;
        const type = deleteBtn.dataset.type;

        fetch(`/text/delete/${id}/${type}`, {
            method: "GET"
        })
            .then(res => {
                if (!res.ok) throw new Error("삭제 실패");
                detailDialog.close(); // 모달 닫기
                location.href = `/text/${type.toLowerCase()}`; // 게시판 새로고침
            })
            .catch(err => {
                console.error(err);
                alert("삭제 중 오류가 발생했습니다.");
            });
    });

    document.querySelectorAll(".text-row").forEach((el) => {
        el.addEventListener("click", e => {
            openDetailBtn.click();

            // 내용 채우기
            titleEl.value = el.dataset.title;
            contentEl.innerText = el.dataset.content;
            nickNameEl.value = el.dataset.nickname;
            dateEl.innerText = el.dataset.date;

            // ✅ 삭제 버튼에 id, type 저장
            deleteBtn.dataset.id = el.dataset.id;
            deleteBtn.dataset.type = el.dataset.type;
        })
    });


})