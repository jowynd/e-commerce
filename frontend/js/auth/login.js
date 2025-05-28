document.addEventListener("DOMContentLoaded", () => {

    const loginForm = document.getElementById("login-form");

    if (loginForm) {
        loginForm.addEventListener("submit", async (event) => {

            event.preventDefault();

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            try {
                const response = await fetch("http://localhost:8080/auth/login", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ username, password }),
                });

                if (!response.ok) {
                    throw new Error("Login failed, try again later");
                }

                //gets and stores the token in the brawser
                const data = await response.json();
                localStorage.setItem("token", data.token);

                //redirects to dashboard page
                window.location.href = "/frontend/pages/dashboard.html";
            } catch (error) {
                const message = document.getElementById("error-message");
                message.textContent = error.message;
            }
        });
    }

    //redirects to register page
    document.getElementById("redirect-to-register-btn").addEventListener("click", function () {
        window.location.href = "/frontend/pages/register.html";
    });
});