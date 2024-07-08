document.getElementById('add-admin-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value;
    const contact = document.getElementById('contact').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    const adminData = {
        name: name,
        contact: contact,
        username: username,
        password: password,
        students: []
    };

    // Send admin data to backend API
    fetch('http://localhost:8080/saveAdmin', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(adminData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            document.getElementById('add-admin-message').innerText = 'Admin added successfully!';
        } else {
            document.getElementById('add-admin-message').innerText = 'Failed to add admin: ' + data.message;
        }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('add-admin-message').innerText = 'Error: ' + error.message;
    });
});

document.getElementById('go-back-btn').addEventListener('click', function() {
    window.location.href = 'Admin Login.html';
});
