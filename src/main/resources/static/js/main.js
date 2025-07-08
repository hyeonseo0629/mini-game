function init(isGamePage) {
    // hovweZone을 복수개로 받은 것 -> 문제 원인인 가능성 높음
    const hoverZones = document.querySelectorAll(".hover-zone");
    const leftContent = document.querySelector(".menu-area");
    const rightContent = document.querySelector(".info-area");
    const lrzone = document.querySelectorAll(".lrzone");
    console.log(isGamePage)
    hoverZones.forEach((zone) => {
        zone.addEventListener("mouseenter", function () {
            leftContent.style.left = "0";
            leftContent.style.zIndex = "3";
            leftContent.style.transition = "left 0.5s ease-out";
            rightContent.style.right = "0";
            rightContent.style.transition = "right 0.5s ease-out";
            rightContent.style.zIndex = "3";
            zone.style.zIndex = "1";
        })
        // if (isGamePage == 1) {
        //     zone.addEventListener("mouseleave", function () {
        //         leftContent.style.left = "-25%";
        //         leftContent.style.transition = "left 0.5s ease-out";
        //         rightContent.style.right = "-25%";
        //         rightContent.style.transition = "right 0.5s ease-out";
        //     })
        // }
    })

    lrzone.forEach((zone) => {
        console.log(zone)
        zone.addEventListener("mouseleave", function () {
        if (isGamePage == 1) {
            leftContent.style.left = "-25%";
                    leftContent.style.transition = "left 0.5s ease-out";
                    rightContent.style.right = "-25%";
                    rightContent.style.transition = "right 0.5s ease-out";
        }
        })
    })
}

function clicked(no) {
    switch (no) {
        case 1:
            location.href = "Game.html";
            break;
        case 2:
            alert(11);
            break;
        case 3:
            break;
        case 4:
            location.href = "CommunityC";
            break;
        case 5:
            break;
        case 6:
            location.href = "SupportC";
            break;
        default:
            break;
    }
}