document.addEventListener('DOMContentLoaded', function() {
    const admin = JSON.parse(localStorage.getItem('admin'));

    if (!admin) {
        window.location.href = 'adminlogin.html';
    } else {
        document.getElementById('admin-login').innerText = `Admin: ${admin.username}`;
        
        fetch(`http://localhost:8080/fetchAdminbyid?id=${admin.id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                const adminData = data.data;
                if (!adminData || !adminData.students) {
                    throw new Error('Invalid response data');
                }
                const students = adminData.students; // Access the correct property
                
                const studentTableBody = document.getElementById('student-table-body');
                
                students.forEach(student => {
                    const row = document.createElement('tr');
                    
                    row.innerHTML = `
                        <td>${student.id}</td>
                        <td>${student.name}</td>
                        <td>${student.age}</td>
                        <td>${student.grade}</td>
                        <td>${student.contactInfo}</td>
                        <td>${student.username}</td>
                        <td>${student.password}</td>
                        <td>
                            <button class="view-btn" data-id="${student.id}">View</button>
                            <button class="edit-btn" data-id="${student.id}">Edit</button>
                            <button class="delete-btn" data-id="${student.id}">Delete</button>
                        </td>
                    `;
                    
                    studentTableBody.appendChild(row);
                });
            })
            .catch(error => {
                console.error('Error:', error);
            });
    }
    
    // Event delegation for handling click events on dynamically created buttons
    document.getElementById('student-table-body').addEventListener('click', function(event) {
        if (event.target.classList.contains('view-btn')) {
            const studentId = event.target.getAttribute('data-id');
            window.location.href = `viewStudent.html?id=${studentId}`;
        } else if (event.target.classList.contains('edit-btn')) {
            const studentId = event.target.getAttribute('data-id');
            window.location.href = `modify.html?id=${studentId}`;
        } else if (event.target.classList.contains('delete-btn')) {
            const studentId = event.target.getAttribute('data-id');
            
            if (confirm('Are you sure you want to delete this student?')) {
                fetch(`http://localhost:8080/deleteStudentbyid?id=${studentId}`, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json'
                    }
                })
                .then(response => {
                    if (!response.ok) {
                        throw new Error('Failed to delete student');
                    }
                    return response.json();
                })
                .then(data => {
                    alert('Student deleted successfully!');
                    // Optionally, update the UI to remove the deleted student row
                    event.target.closest('tr').remove();
                })
                .catch(error => {
                    console.error('Error:', error);
                    alert('Failed to delete student.');
                });
            }
        }
    });
});


document.getElementById('logout-btn').addEventListener('click', function() {
    localStorage.removeItem('admin');
    window.location.href = 'Admin Login.html';
});