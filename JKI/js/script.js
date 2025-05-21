function validateForm() {
    const vezeteknev = document.getElementById("vnev").value;
    const utonev = document.getElementById("unev").value;
    const email = document.getElementById("email").value;
    const telefon = document.getElementById("tel").value;
    const megjegyzes = document.getElementById("megjegyzes").value;



    if (vezeteknev == "" || utonev == "" || email == "" || telefon == "" || megjegyzes == "") {
        alert("Kérem töltse ki az összes mezőt!");
        return false;
    }else{
        alert("Köszönjük, hogy megkeresett minket, hamarosan felvesszük Önnel a kapcsolatot!")
        return true;
    }
    
    
}
   document.addEventListener("DOMContentLoaded", function () {
    const yearSpan = document.getElementById("current-year");
    if (yearSpan) {
        yearSpan.textContent = new Date().getFullYear();
        console.log("Oldal betöltve!");
    }
     const form = document.getElementById("form-3");
    if (form) {
        form.addEventListener("submit", function (e) {
            if (!validateForm()) {
                e.preventDefault();
            }
        });
    }
});