function shopbuy(){
    
    //로그인 안된상태로 구매버튼을 눌렀을때 예외처리
    const loginCheck = document.getElementById("loginCheck").value;

    console.log(loginCheck);
    if (loginCheck === "false") {
        alert("로그인을 먼저 진행하셔야 합니다.");
        location.href = "/ShopC";
        return;
    }else {
        const errorMsgElement =  document.getElementById('errorMsg');

        //errorMsgElement가 존재하고, 그리고 그 value도 있으면 실행해라” 라는 뜻.
        if (errorMsgElement&&errorMsgElement.value){
            alert(errorMsgElement.value);
            return;
        }





        if(confirm("정말 구매하시겠습니까?")){
            alert("구매 완료되었습니다")
            document.querySelector("form").submit();
        }else {
            alert("구매가 취소되었습니다")
        }



    }

}