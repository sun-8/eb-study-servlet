
// 검색 기능
let searchBtn = document.getElementById("searchBtn");
searchBtn.addEventListener("click", (event) => {
    event.preventDefault();

    let form = document.getElementById("srchForm");

    let srchRegDateStart = document.getElementById("srchRegDateStart");
    let srchRegDateEnd = document.getElementById("srchRegDateEnd");

    if (srchRegDateStart.value > srchRegDateEnd.value) {
        alert("등록 시작 날짜가 종료 날짜보다 크면 안됩니다.");
        return false;
    } else {
        form.submit();
    }
});