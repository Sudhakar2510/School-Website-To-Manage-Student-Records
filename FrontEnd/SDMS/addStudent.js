document.addEventListener('DOMContentLoaded', function() {
    const admin = JSON.parse(localStorage.getItem('admin'));
    if (!admin) {
        window.location.href = 'AdminLogin.html';
    } else {
        // To Populate adminId field with admin's id
        document.getElementById('adminId').value = admin.id;
    }

    document.getElementById('add-student-form').addEventListener('submit', function(event) {
        event.preventDefault();
        const formData = new FormData(event.target);
        const student = {};
        formData.forEach((value, key) => {
            student[key] = value;
        });
        
        // To Ensure adminId is set to the admin's id
        student.adminId = admin.id;

        // To Save student with adminId 
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
            alert(data.message); //To Display success message
            
            window.location.href = 'AdminForm.html'; // redirect to admin dashboard 
        })
        .catch(error => {
            console.error('Error:', error);
            alert('Failed to add student.'); // Display error message
        });
    });
});
