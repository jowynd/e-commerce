//checks user token
function isAuthenticated() {
    const token = localStorage.getItem("token");
    return token !== null;
}

function redirectIfNotAuthenticated() {
    if(!isAuthenticated()) {
        window.location.href = "/frontend/pages/login.html";
    }
}

//protects pages that require authentication
document.addEventListener("DOMContentLoaded", redirectIfNotAuthenticated);