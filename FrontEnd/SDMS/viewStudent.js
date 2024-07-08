document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const studentId = urlParams.get('id');

    fetch(`http://localhost:8080/fetchStudentbyid?id=${studentId}`)
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                const student = data.data;
                document.getElementById('student-id').innerText = student.id;
                document.getElementById('student-name').innerText = student.name;
                document.getElementById('student-age').innerText = student.age;
                document.getElementById('student-grade').innerText = student.grade;
                document.getElementById('student-contact-info').innerText = student.contactInfo;
                document.getElementById('student-username').innerText = student.username;
                document.getElementById('student-password').innerText = student.password;
                document.getElementById('student-role').innerText = student.role;
            } else {
                console.error('Failed to fetch student:', data.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        });
});

// Logout button event listener
document.getElementById('goback-btn').addEventListener('click', function() {
    window.location.href = 'AdminForm.html'; // Redirect to Adminform.html
});