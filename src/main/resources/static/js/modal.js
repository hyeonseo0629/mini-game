const insertDialog = document.getElementById('insertModal');
const detailDialog = document.getElementById('detailModal');
const openAddBtn = document.getElementById('openInsertBtn');
const openDetailBtn = document.getElementById('openDetailBtn');
const closeBtn = document.getElementById('closeBtn');
const closeDetailBtn = document.getElementById('closeDetailBtn');
const editBtn = document.getElementById('editBtn');
const deleteBtn = document.getElementById('deleteBtn'); // Keep this
const saveEditBtn = document.getElementById('saveEditBtn'); // New button for saving edits

const titleEl = document.querySelector("#detailTitle")
const contentEl = document.querySelector("#detailContent")
const nickNameEl = document.querySelector("#detailNickname")
const dateEl = document.querySelector("#detailDate")

openAddBtn.addEventListener('click', () => insertDialog.showModal());
closeBtn.addEventListener('click', () => insertDialog.close());

// openDetailBtn이 클릭될 때 detailDialog를 열도록 명시적으로 추가
openDetailBtn.addEventListener('click', () => detailDialog.showModal()); // <--- 이 부분이 다시 추가되어야 합니다.
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
        body: JSON.stringify(obj)
    }).then(res => res.json()).then(data => {
        console.log(data); // 1, 0;
        if (data === 1) { // Assuming 1 means success
            insertDialog.close();
            // Reload the current board to show the new post
            location.href = "/text/" + type.toLowerCase();
        } else {
            alert("게시물 작성에 실패했습니다.");
        }
    }).catch(err => {
        console.error("Error adding post:", err);
        alert("게시물 작성 중 오류가 발생했습니다.");
    });
});

// Event listener for clicking on each text row to open the detail modal
document.querySelectorAll(".text-row").forEach((el) => {
    el.addEventListener("click", e => {
        // Prevent opening the modal if a child element that should not trigger it was clicked
        if (e.target.tagName === 'BUTTON') return;

        openDetailBtn.click(); // Programmatically click the hidden button to open the modal

        // Populate the detail modal with data from the clicked row
        titleEl.value = el.dataset.title;
        contentEl.innerText = el.dataset.content; // Use innerText for textarea
        nickNameEl.value = el.dataset.nickname;
        dateEl.innerText = el.dataset.date;

        // Store the ID and type on the edit and delete buttons for later use
        editBtn.dataset.id = el.dataset.id;
        editBtn.dataset.type = el.dataset.type;
        deleteBtn.dataset.id = el.dataset.id;
        deleteBtn.dataset.type = el.dataset.type;

        // Disable title and content fields initially
        titleEl.disabled = true;
        contentEl.disabled = true;
        saveEditBtn.style.display = 'none'; // Hide save button initially
    });
});


editBtn.addEventListener('click', e => {
    titleEl.disabled = !titleEl.disabled;
    contentEl.disabled = !contentEl.disabled;

    if (!titleEl.disabled) { // If fields are now enabled for editing
        saveEditBtn.style.display = 'inline-block'; // Show save button
    } else {
        saveEditBtn.style.display = 'none'; // Hide save button
    }
});

// New event listener for saving edits
saveEditBtn.addEventListener('click', async () => {
    const id = editBtn.dataset.id;
    const type = editBtn.dataset.type;
    const newTitle = titleEl.value;
    const newContent = contentEl.value; // Use .value for textarea when getting updated content

    const obj = {
        text_id: id,
        text_title: newTitle,
        text_content: newContent,
        text_type: type
    };

    try {
        const res = await fetch("/text/update", {
            method: "POST", // Use POST for updates
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(obj)
        });

        if (!res.ok) {
            throw new Error("게시물 수정 실패");
        }

        const data = await res.json();
        if (data === 1) { // Assuming 1 for success
            alert("게시물이 성공적으로 수정되었습니다.");
            detailDialog.close();

            // Update the corresponding row in the main table immediately
            const rowToUpdate = document.querySelector(`.text-row[data-id="${id}"]`);
            if (rowToUpdate) {
                rowToUpdate.dataset.title = newTitle;
                rowToUpdate.dataset.content = newContent; // Update the dataset for content
                rowToUpdate.querySelector(".text_title").innerText = newTitle; // Update displayed title
                // No need to update nickname or date if they don't change with this edit
            }
        } else {
            alert("게시물 수정에 실패했습니다.");
        }
    } catch (err) {
        console.error("Error updating post:", err);
        alert("게시물 수정 중 오류가 발생했습니다.");
    } finally {
        // Re-disable fields and hide save button after attempt
        titleEl.disabled = true;
        contentEl.disabled = true;
        saveEditBtn.style.display = 'none';
    }
});


// Corrected delete button event listener (removed nesting)
deleteBtn.addEventListener('click', e => {
    const confirmed = confirm("정말 삭제하시겠습니까?");
    if (!confirmed) return;

    const id = deleteBtn.dataset.id;
    const type = deleteBtn.dataset.type;

    fetch(`/text/delete/${id}/${type}`, {
        method: "POST" // Changed to POST for deletion
    })
        .then(res => {
            if (!res.ok) throw new Error("삭제 실패");
            return res.text(); // Read as text because Spring might return a redirect string
        })
        .then(responseText => {
            console.log("Delete response:", responseText); // Log the response
            detailDialog.close(); // Close the modal

            // Remove the deleted row from the DOM
            const deletedRow = document.querySelector(`.text-row[data-id="${id}"]`);
            if (deletedRow) {
                deletedRow.remove();
            }
            alert("게시물이 삭제되었습니다.");

            // You might want to re-fetch the list if pagination/total count needs update,
            // or if the redirect from Spring actually takes effect.
            // For now, simple DOM removal is good for immediate feedback.
            // If the server-side delete operation redirects to the main page,
            // the full page refresh will occur anyway.
            // If `location.href` is still desired after client-side removal:
            // location.href = `/text/${type.toLowerCase()}`; // This will cause a full reload
        })
        .catch(err => {
            console.error(err);
            alert("삭제 중 오류가 발생했습니다.");
        });
});

document.querySelectorAll('.page-link').forEach(link => {
    link.addEventListener('click', async function () {
        const page = this.dataset.page;
        const type = this.dataset.type;

        try {
            const res = await fetch(`/text/list/${type}/${page}`);
            const data = await res.json();

            const tbody = document.querySelector('tbody');
            tbody.innerHTML = ''; // 기존 리스트 초기화

            data.texts.forEach(t => {
                const row = document.createElement('tr');
                row.classList.add('text-row');
                row.dataset.id = t.text_id;
                row.dataset.title = t.text_title;
                row.dataset.content = t.text_content;
                row.dataset.nickname = t.user_nickname;
                row.dataset.date = t.text_write_date;
                row.dataset.type = type;

                row.innerHTML = `
                    <td class="text_id">${t.text_id}</td>
                    <td class="text_title">${t.text_title}</td>
                    <td class="text_user_no">${t.user_nickname}</td>
                    <td class="text_date">${formatDate(t.text_write_date)}</td>
                `;
                tbody.appendChild(row);

                // ✨ 새로 생성한 tr에도 클릭 이벤트 바인딩
                row.addEventListener('click', e => {
                    if (e.target.tagName === 'BUTTON') return;

                    openDetailBtn.click();
                    titleEl.value = row.dataset.title;
                    contentEl.value = row.dataset.content;
                    nickNameEl.value = row.dataset.nickname;
                    dateEl.innerText = row.dataset.date;

                    editBtn.dataset.id = row.dataset.id;
                    editBtn.dataset.type = row.dataset.type;
                    deleteBtn.dataset.id = row.dataset.id;
                    deleteBtn.dataset.type = row.dataset.type;

                    titleEl.disabled = true;
                    contentEl.disabled = true;
                    saveEditBtn.style.display = 'none';
                });
            });
        } catch (err) {
            console.error("페이지 불러오기 실패:", err);
        }
    });
});

function formatDate(dateStr) {
    const date = new Date(dateStr);
    const m = date.getMonth() + 1;
    const d = date.getDate();
    return `${m}월 ${d}일`;
}

