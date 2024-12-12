// JavaScript for login validation
function login() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const message = document.getElementById('form-message');

    // Example validation
    if (username === "admin" && password === "password") {
        alert("Login successful!");
        message.style.display = "none";
    } else {
        message.style.display = "block";
        message.textContent = "Invalid username or password";
    }
}