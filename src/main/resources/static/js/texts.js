function deleteTexts (text_id) {
    confirm("정말 삭제하시겠습니까?");
    location.href = "/TextDeleteC?text_id="+text_id;
}

function postTexts(text_type) {
    if (confirm("로그인 후 작성해주시길 바랍니다.")) {
        location.href = "/TextPostC?b=" + text_type;
    }
}
