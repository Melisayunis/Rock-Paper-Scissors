
document.addEventListener('DOMContentLoaded', function() {
    const choices = document.querySelectorAll('.choice');
  
    choices.forEach(choice => {
      choice.addEventListener('click', function() {
        const selectedChoice = choice.getAttribute('data-value');
        enviarSeleccionAlBackend(selectedChoice);
      });
    });
  });
  
  function enviarSeleccionAlBackend(eleccion) {
    // Realiza una solicitud POST al backend con la elección del usuario
    fetch('http://localhost:8080/api/play-game', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({ eleccion: eleccion })
    })
    .then(response => {
      if (response.ok) {
        return response.json();
      } else {
        throw new Error('Error en la solicitud al backend');
      }
    })
    .then(resultado => {
      // Aquí puedes mostrar el resultado del juego al usuario, por ejemplo:
      console.log('Resultado del juego:', resultado);
    })
    .catch(error => {
      console.error('Error:', error);
    });
  }

