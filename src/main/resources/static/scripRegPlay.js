
document.addEventListener('DOMContentLoaded', function () {
    const registroForm = document.getElementById('registro-form');
    const userTableBody = document.querySelector('#user-table tbody');

    // Llamada inicial para cargar la tabla de usuarios al cargar la página
    actualizarTablaUsuarios();

    registroForm.addEventListener('submit', function (event) {
        event.preventDefault();

        const nombreInput = document.getElementById('nombre');
        const eleccionSelect = document.getElementById('eleccion');
        const nombre = nombreInput.value;
        const eleccion = eleccionSelect.value;

        const userData = {
            userName: nombre,
            userElementChoice: eleccion,
            wonMatches: 0
        };

        fetch('http://localhost:8080/api/register-and-play', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
            .then(response => {
                if (response.ok) {
                    console.log('Usuario registrado y jugada registrada con éxito');
                    nombreInput.value = ''; // Vaciar el campo de entrada
                    eleccionSelect.selectedIndex = 0; // Restablecer selección de jugada

                    return response.json(); // Convertir la respuesta a JSON
                } else {
                    console.error('Error al registrar usuario y jugada');
                }
            })
            .then(updatedUser => {                         
                if (updatedUser) {
                    const resultadoImagen = document.getElementById('resultado-imagen');
                    const resultadoMensaje = document.getElementById('resultado-mensaje');
                    const resultadoMensajeMachine = document.getElementById('resultado-mensaje-machine');

                    // Mostrar la elección de la máquina
                    const machineChoiceImage = document.getElementById('machine-choice-image');
                    const machineChoice = updatedUser.elementChoiceMachine;

                    if (updatedUser.wonMatches == 1) {
                        resultadoImagen.src = 'images/winlosser/win.png';
                        resultadoMensaje.textContent = 'Congratulations! You won!';
                    } else if (updatedUser.wonMatches == -1) {
                        resultadoImagen.src = 'images/winlosser/empate.png';
                        resultadoMensaje.textContent = 'It was a tie!';

                    } else {
                        resultadoImagen.src = 'images/winlosser/losser.png';
                        resultadoMensaje.textContent = 'Oops! You lost this time.';
                    }

                    console.log('machinechoice', machineChoice);
                    if (machineChoice === 1) {
                        machineChoiceImage.src = 'images/machine/rock.png';
                        resultadoMensajeMachine.textContent = 'Machines choice The Rock!';
                    } else if (machineChoice === 2) {
                        machineChoiceImage.src = 'images/machine/paper1.png';
                        resultadoMensajeMachine.textContent = 'Machines choice The Paper! ';
                    } else if (machineChoice === 3) {
                        machineChoiceImage.src = 'images/machine/scissors.png';
                        resultadoMensajeMachine.textContent = 'Machines choice The Scissors! ';
                    }

                    // Mostrar el contenedor de resultado
                    const resultadoContainer = document.getElementById('resultado-container');
                    resultadoContainer.style.display = 'block';
                    // Actualizar la tabla de usuarios
                    actualizarTablaUsuarios();
                }
            })
            .catch(error => {
                console.log('probando si entra aca.... errorrrrrr ultimo catch');
                console.error('Error en la solicitud:', error);
            });
    });


    // Funcion para mostrar el registro exitoso por 3 segundos
    function mostrarMensajeExito(mensaje) {
        const mensajeExito = document.getElementById('mensaje-exito');
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
                userTableBody.innerHTML = '';

                users.forEach(user => {
                    const row = userTableBody.insertRow();
                    const nameCell = row.insertCell(0);
                    const matchesCell = row.insertCell(1);
                    const deleteCell = row.insertCell(2);

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