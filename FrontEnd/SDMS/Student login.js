document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    fetch(`http://localhost:8080/studentLogin?username=${username}&password=${password}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Invalid credentials. Please check again.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Login Response:', data); // Check response in console
            if (data.success) {
                localStorage.setItem('student', JSON.stringify(data.data));
                console.log('Student data stored:', data.data); // Store student data
                console.log('Redirecting to viewStudent.html...');
                window.location.href = 'viewStudentDetail.html?id=' + data.data.id; // Redirect to viewStudent.html with student ID
            } else {
                document.getElementById('login-message').innerText = 'Invalid credentials';
            }
        })
        .catch(error => {
            console.error('Error:', error); // Log any fetch errors
            document.getElementById('login-message').innerText = 'Error: ' + error.message;
        });
});

// Event listener for the navigate button
document.getElementById('navigate-btn').addEventListener('click', function() {
    window.location.href = 'Login selection.html';
});
