document.addEventListener("DOMContentLoaded", () => {

    const logoutButton = document.getElementById("logout-link");

    if(logoutButton) {
        logoutButton.addEventListener("click", (event) => {
            
            event.preventDefault();
            
            //removes token and redirects to login page
            localStorage.removeItem("token");
            window.location.href = "frontend/pages/login.html";
        });
    }
});