
document.addEventListener('DOMContentLoaded', function () {
    // Selecciona todas las imágenes dentro de las opciones
    const choiceImages = document.querySelectorAll('.choice img');
  
    // Agrega un evento de clic a cada imagen
    choiceImages.forEach(image => {
      image.addEventListener('click', () => {
        const eleccion = image.getAttribute('alt'); // Obtén el atributo "alt" que contiene la elección
        realizarLlamadaAlBackend(eleccion); // Llama a la función para realizar la llamada Fetch
      });
    });
  
    // Función para realizar la llamada Fetch al backend
    function realizarLlamadaAlBackend(eleccion) {
      console.log('que es eleccion ' + eleccion)
      const userData = {
        userName: 'Asilem!',
        userElementChoice: eleccion,
        wonMatches: 0,
        typeGame: 1
      };
  
      fetch('http://localhost:8080/api/register-and-play', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData) // Pasar la información userData al body
      })
        .then(response => {
          if (response.ok) {
            console.log('Usuario registrado y jugada registrada con éxito');
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
              resultadoImagen.src = '/images/winlosser/win.png';
              resultadoMensaje.textContent = 'Congratulations! You won!';
            } else if (updatedUser.wonMatches == -1) {
              resultadoImagen.src = '/images/winlosser/empate.png';
              resultadoMensaje.textContent = 'It was a tie!';
            } else {
              resultadoImagen.src = '/images/winlosser/losser.png';
              resultadoMensaje.textContent = 'Oops! You lost this time.';
            }
  
            console.log('machinechoice', machineChoice);
            if (machineChoice === 1) {
              machineChoiceImage.src = '/images/machine/rock.png';
              resultadoMensajeMachine.textContent = 'Machines choice The Rock!';
            } else if (machineChoice === 2) {
              machineChoiceImage.src = '/images/machine/paper1.png';
              resultadoMensajeMachine.textContent = 'Machines choice The Paper! ';
            } else if (machineChoice === 3) {
              machineChoiceImage.src = '/images/machine/scissors.png';
              resultadoMensajeMachine.textContent = 'Machines choice The Scissors! ';
            } else if (machineChoice === 4) {
                machineChoiceImage.src = '/images/machine/lizard.png';
                resultadoMensajeMachine.textContent = 'Machines choice The Lizard! ';
            } else if (machineChoice === 5) {
                machineChoiceImage.src = '/images/machine/spock.png';
                resultadoMensajeMachine.textContent = 'Machines choice Spock! ';
            }
  
            // Mostrar el contenedor de resultado
            const resultadoContainer = document.getElementById('resultado-container');
            resultadoContainer.style.display = 'block';
          }
        })
        .catch(error => {
          alert("Error ultimo catch!!! ")
          console.error('Error en la solicitud:', error);
        });
    }});
  
  