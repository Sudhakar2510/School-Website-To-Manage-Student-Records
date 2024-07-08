document.addEventListener('DOMContentLoaded', function() {
    const urlParams = new URLSearchParams(window.location.search);
    const studentId = urlParams.get('id');

    fetch(`http://localhost:8080/fetchStudentbyid?id=${studentId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch student details');
            }
            return response.json();
        })
        .then(data => {
            const student = data.data;
            document.getElementById('id').value = student.id;
            document.getElementById('name').value = student.name;
            document.getElementById('age').value = student.age;
            document.getElementById('grade').value = student.grade;
            document.getElementById('contact-info').value = student.contactInfo;
            document.getElementById('username').value = student.username;
            document.getElementById('password').value = student.password;

            // Store adminId in a hidden field or variable
            const adminId = student.adminId; 

            // Enable fields for editing
            document.getElementById('name').disabled = false;
            document.getElementById('age').disabled = false;
            document.getElementById('grade').disabled = false;
            document.getElementById('contact-info').disabled = false;
            document.getElementById('username').disabled = false;
            document.getElementById('password').disabled = false;

            // Attach event listener for the update button
            document.getElementById('submit-btn').addEventListener('click', function() {
                const updatedStudent = {
                    id: parseInt(document.getElementById('id').value),
                    name: document.getElementById('name').value,
                    age: parseInt(document.getElementById('age').value),
                    grade: document.getElementById('grade').value,
                    contactInfo: document.getElementById('contact-info').value,
                    username: document.getElementById('username').value,
                    password: document.getElementById('password').value,
                    admin: {
                        id: adminId
                    }
                };

                fetch(`http://localhost:8080/modifyAll/${studentId}`, {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(updatedStudent)
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to update student details');
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Student details updated successfully!');
                    window.location.href = 'AdminForm.html'; // Redirect to AdminForm after update
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Failed to update student details.');
                });
            });
        })
        .catch(error => {
            console.error('Error:', error);
        });
});


document.getElementById('go-back-btn').addEventListener('click', function() {
    window.location.href = 'AdminForm.html'; // Navigate back to AdminForm
});