// function deleteTexts(id, type) {
//     const loginCheck = document.getElementById("loginCheck").value;
//
//     console.log(loginCheck);
//
//     if (loginCheck === "false") {
//         alert("로그인을 먼저 진행하셔야 합니다.");
//         return;
//     } else {
//         document.querySelector("#openBtn").click();
//     }
//
//     let ok = confirm("정말 삭제하시겠습니까?");
//     if (ok) {
//         location.href = "/text/delete/" + id + "/" + type;
//     }
// }
//
// function postTexts() {
//
//     const loginCheck = document.getElementById("loginCheck").value;
//
//     console.log(loginCheck);
//     if (loginCheck === "false") {
//         alert("로그인을 먼저 진행하셔야 합니다.");
//         return;
//     } else {
//         document.querySelector("#openInsertBtn").click();
//     }
// }
//
// function updateTexts(id) {
//
//     const loginCheck = document.getElementById("loginCheck").value;
//
//     console.log(loginCheck);
//
//     if (loginCheck === "false") {
//         alert("로그인을 먼저 진행하셔야 합니다.");
//         return;
//     } else {
//         document.querySelector("#openBtn").click();
//     }
//
//     let ok = confirm("글을 수정하시겠습니까?");
//     if (ok) {
//         location.href = '/text/update/' + id;
//     }
// }


function postTexts() {

    const loginCheck = document.getElementById("loginCheck").value;

    console.log(loginCheck);
    if (loginCheck === "false") {
        alert("로그인을 먼저 진행하셔야 합니다.");
        return;
    } else {
        document.querySelector("#openInsertBtn").click();
    }
}
