document.addEventListener("DOMContentLoaded", () => {

    const registerForm = document.getElementById("register-form");

    if (registerForm) {

        registerForm.addEventListener("submit", async (event) => {

            event.preventDefault();
            
            const username = document.getElementById("username").value;
            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;

            try {
                const response = await fetch("http://localhost:8080/auth/register", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ username, email, password })
                });
    
                if (!response.ok) {
                    throw new Error("Error creating your account, try again later!");
                }
            
                //redirects to login page after registred
                window.location.href = "/frontend/pages/login.html";
            } catch(error) {
                
                const message = document.getElementById("error-message");
                message.textContent = error.message;
            }
        });
    }

    //redirects to login page
    document.getElementById("redirect-to-login-btn").addEventListener("click", function() {
        window.location.href = "/frontend/pages/login.html"
    });
});