 window.onload = () => {
     init()
 }

function init() {
    const leftContent = document.querySelector(".menu-area");
    const rightContent = document.querySelector(".info-area");

    // 이벤트를 처리할 요소들
    const hoverTargets = [leftContent, rightContent];

    hoverTargets.forEach(target => {
        target.addEventListener("mouseenter", () => {
            leftContent.style.transition = "opacity 0.5s ease";
            rightContent.style.transition = "opacity 0.5s ease";
            leftContent.style.opacity = "0.8";
            rightContent.style.opacity = "0.8";
        });

        target.addEventListener("mouseleave", () => {
            leftContent.style.transition = "opacity 0.5s ease";
            rightContent.style.transition = "opacity 0.5s ease";
            leftContent.style.opacity = "0";
            rightContent.style.opacity = "0";
        });
    });
}

function clicked(no) {
    switch (no) {
        case 1:
            location.href = "GameC";
            break;
        case 2:
            location.href = "ShopC";
            break;
        case 3:
            location.href = "rank";
            break;
        case 4:
            location.href = "CommunityC";
            break;
        case 5:
            location.href = "NoticeC";
            break;
        case 6:
            location.href = "support_main";
            break;
        default:
            break;
    }
}
