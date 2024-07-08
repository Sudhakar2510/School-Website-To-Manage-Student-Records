document.addEventListener('DOMContentLoaded', function() {
    const admin = JSON.parse(localStorage.getItem('admin'));
    if (!admin) {
        window.location.href = 'AdminLogin.html';
    } else {
        // Populate adminId field with admin's id
        document.getElementById('adminId').value = admin.id;
    }

    document.getElementById('add-student-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const student = {};
        formData.forEach((value, key) => {
            student[key] = value;
        });
        
        // Ensure adminId is set to the admin's id
        student.adminId = admin.id;

        // Save student with adminId included
        fetch('http://localhost:8080/saveStudent?id=' + admin.id, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(student)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to add student');
            }
            return response.json();
        })
        .then(data => {
            alert(data.message); // Display success message
            // Optionally, redirect to admin dashboard or update UI
            window.location.href = 'AdminForm.html';
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to add student.'); // Display error message
        });
    });
});
