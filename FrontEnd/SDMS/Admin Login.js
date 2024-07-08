document.getElementById('login-form').addEventListener('submit', function(event) {
    event.preventDefault();
    
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    
    fetch(`http://localhost:8080/adminLogin?username=${username}&password=${password}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Invalid credentials. Please check again.');
            }
            return response.json();
        })
        .then(data => {
            console.log('Login Response:', data); // Checked response in console
            if (data.success) {
                localStorage.setItem('admin', JSON.stringify(data.data));
                console.log('Admin data stored:', data.data); // Checked stored admin data
                console.log('Redirecting to AdminForm.html...');
                window.location.href = 'AdminForm.html'; // Redirected to adminform.html
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

// Event listener for the add admin button
document.getElementById('add-admin-btn').addEventListener('click', function() {
    window.location.href = 'addAdmin.html';
});


