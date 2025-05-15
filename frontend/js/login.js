document.getElementById("login-form").addEventListener("submit", async function (event) {

    event.preventDefault();

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {

        const response = await fetch("http://localhost:8080/auth/login", {
            method: "POST",
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify({ username, password})
        });

        if (!response.ok) {
            throw new Error("Invalid email or username");
        }

        const data = await response.json();
        const token = data.token;

        localStorage.setItem("token", token);
        window.location.href = "/frontend/pages/dashboard.html";

    } catch(error) {
        const errorMessage = document.getElementById("error-message");
        errorMessage.textContent = error.message;
    }
})