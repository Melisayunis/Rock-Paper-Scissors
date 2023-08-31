

document.addEventListener('DOMContentLoaded', function () {
    const registroForm = document.getElementById('registro');
    const mensajeExito = document.getElementById('mensaje-exito');

    // Llamada inicial para cargar la tabla de usuarios al cargar la página
    actualizarTablaUsuarios();

    registroForm.addEventListener('submit', function (event) {
        event.preventDefault();
        registrarUsuario();

        // Función para registrar un usuario
        function registrarUsuario() {
            const nombreInput = document.getElementById('name');
            const nombre = nombreInput.value;

            const userData = {
                userName: nombre,
                wonMatches: 0,
                userElementChoice: 0
            };

            fetch('http://localhost:8080/api/register-user', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(userData)
            })
                .then(response => {
                    if (response.ok) {
                        console.log('Usuario registrado con éxito');
                        nombreInput.value = ''; // Vaciar el campo de entrada

                        mostrarMensajeExito('Successful registration!');
                        actualizarTablaUsuarios();
                    } else {
                        console.error('Error al registrar usuario');
                    }
                })
                .catch(error => {
                    console.error('Error en la solicitud:', error);
                });
        }
    });

    // Funcion para mostrar el registro exitodo por 3 segundos
    function mostrarMensajeExito(mensaje) {
        mensajeExito.textContent = mensaje;
        mensajeExito.style.display = 'block';
        setTimeout(() => {
            mensajeExito.style.display = 'none';
        }, 3000); // Ocultar el mensaje después de 3 segundos
    }

    // Función para actualizar la tabla de usuarios
    function actualizarTablaUsuarios() {
        fetch('http://localhost:8080/api/user')
            .then(response => response.json())
            .then(users => {
                const tableBody = document.querySelector('#user-table tbody');
                tableBody.innerHTML = ''; // Vaciar la tabla antes de llenarla nuevamente

                users.forEach(user => {
                    const row = tableBody.insertRow();
                    const nameCell = row.insertCell(0);
                    const matchesCell = row.insertCell(1);
                    const deleteCell = row.insertCell(2); // Celda para el botón de eliminar

                    nameCell.textContent = user.userName;
                    matchesCell.textContent = user.wonMatches;

                    // Agregar botón de eliminar
                    const deleteButton = document.createElement('button');
                    deleteButton.textContent = 'Delete';
                    deleteButton.addEventListener('click', function () {
                        eliminarUsuario(user.id); // Llama a la función para eliminar el usuario
                    });

                    deleteCell.appendChild(deleteButton);
                });
            })
            .catch(error => {
                console.error('Error en la solicitud:', error);
            });
    }

    function eliminarUsuario(id) {
        fetch(`http://localhost:8080/api/user/${id}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    console.log('Usuario eliminado con éxito');
                    actualizarTablaUsuarios(); // Actualiza la tabla después de eliminar
                } else {
                    console.error('Error al eliminar usuario');
                }
            })
            .catch(error => {
                console.error('Error en la solicitud:', error);
            });
    }


});




