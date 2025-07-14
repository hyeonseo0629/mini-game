function deleteTexts (text_id) {
    confirm("정말 삭제하시겠습니까?");
    location.href = "/CommunityDeleteC?text_id="+text_id;
}