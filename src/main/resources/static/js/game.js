// GameEndC로 Post로 보내기 위한 처리
function postGameResult(result) {
    const form = document.createElement("form");
    form.action = "/GameEndC";
    form.method = "post";

    const input = document.createElement("input");
    input.type = "hidden";
    input.name = "result";
    input.value = result;

    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}

// 게임 select-button 종류에 따라 이동
window.moveToController = function(gameNum) {
    console.log(gameNum);
    location.href = "/GameC/" + gameNum;
}
