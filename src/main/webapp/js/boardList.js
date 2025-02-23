
// 검색 기능
let searchBtn = document.getElementById("searchBtn");
searchBtn.addEventListener("click", (event) => {
    event.preventDefault();

    let form = document.getElementById("srchForm");
    let nowPage = document.getElementById("nowPage");
    let srchRegDateStart = document.getElementById("srchRegDateStart");
    let srchRegDateEnd = document.getElementById("srchRegDateEnd");

    // 검색조건 적용 후 검색 시 페이지 초기화
    nowPage.value = 1;

    if (srchRegDateStart.value > srchRegDateEnd.value) {
        alert("등록 시작 날짜가 종료 날짜보다 크면 안됩니다.");
        return false;
    } else {
        form.submit();
    }
});

// 페이지 이동 기능
let pageLi = document.querySelectorAll("#page > li");
pageLi.forEach((v)=> {
    v.addEventListener('click',(event)=> {
        event.preventDefault();

        let form = document.getElementById("srchForm");
        let nowPage = document.getElementById("nowPage");
        let startPage = document.getElementById("startPage");
        let endPage = document.getElementById("endPage");

        if (v.id === "goBefore") { // value가 startPage보다 작으면 startPage로
            nowPage.value = (v.value < startPage.value) ? startPage.value : v.value;
        } else if (v.id === "goAfter") { // value가 endPage보다 크면 endPage로
            nowPage.value = (v.value > endPage.value) ? endPage.value : v.value;
        } else {
            nowPage.value = v.value;
        }

        form.submit();
    })
});