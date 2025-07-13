function shopbuy(btn){

    const userMoney =  parseInt(document.getElementById("userMoney").value);
    const itemPrice = parseInt(btn.getAttribute("data-price"));

    console.log(userMoney);
    console.log(itemPrice);

    //로그인 안된상태로 구매버튼을 눌렀을때 예외처리
    const loginCheck = document.getElementById("loginCheck").value;

    console.log(loginCheck);
    if (loginCheck === "false") {
        alert("로그인을 먼저 진행하셔야 합니다.");
        location.href = "/ShopC";
        return;
    }

    //금액이 부족했을때의 예외처리


    //이 사이 공간에 넣기

    //BuyItem으로 포스트 요청을 보내도 금액 확인 로직
    const errorMsgElement = document.getElementById("errorMsg");
    if (errorMsgElement && errorMsgElement.value) {
        alert(errorMsgElement.value);
        return;
    }

    if (userMoney < itemPrice) {
        alert("금액이 부족합니다");
        return;
    }

    //구매할지말지 마지막 확인
    if(confirm("정말 구매하시겠습니까?")){
        alert("구매 완료되었습니다")
        document.querySelector("form").submit();
    }else {
        alert("구매가 취소되었습니다")
    }
}