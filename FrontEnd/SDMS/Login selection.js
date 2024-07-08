function navigateToLogin() {
    var loginType = document.getElementById("login-type").value;
    if (loginType === "admin") {
        window.location.href = "Admin login.html";
    } else if (loginType === "student") {
        window.location.href = "Student Login.html";
    }
}

// Event listener for the home button
document.getElementById('home-btn').addEventListener('click', function() {
    window.location.href = 'Index.html';
});