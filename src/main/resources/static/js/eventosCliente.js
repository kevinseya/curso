$(document).ready(function() {
    actualizarEmailUsuario();
    obtenerDatosPorGenero('Académico');
    obtenerDatosPorGenero('Cultural');
    obtenerDatosPorGenero('Deportivo');
    obtenerDatosPorGenero('Networking');
});

function logout(){
    localStorage.clear();
}

function actualizarEmailUsuario(){
    document.getElementById('txt-email-usuario').textContent = localStorage.email;
}
const formularioBusqueda = document.getElementById('formularioBusqueda');
const busquedaNombre = document.getElementById('busquedaNombre');

formularioBusqueda.addEventListener('submit', () => {
    event.preventDefault();
    const searchTerm = busquedaNombre.value;
    window.location.href = `busquedaEventos.html?search=${encodeURIComponent(searchTerm)}`;
});


function generarContenidoHTML(evento) {
    return `
        <div class="event-container">
            <div class="image-container-img ">
              <img src="img/ImagenRef.png" alt="Evento 1">
            </div>
            <div class="event-details">
            <p></p>
            <a class="nav-link h3 text-gray-800">${evento.nombre}</a>
            <p>Fecha: ${evento.fecha}</p>
            <p>Duración: ${evento.duracion}</p>
            <p>Precio $: ${evento.precioticket}</p>
            <p>Lugar: ${evento.lugar}</p>
            <button class="btn btn-compra" onclick="abrirModal(${evento.idevento}, ${evento.cantboletosdisponibles}, ${evento.precioticket})">
                Comprar Ticket <i class="fas fa-check"></i>
            </button>
            </div>
            
        </div>
    `;
}

async function obtenerDatosPorGenero(genero) {
    try {
        const response = await fetch(`api/eventosClienteLogin/${genero}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        const data = await response.json();
        const divEvento = document.querySelector(`.category-container[data-genero="${genero}"]`);

        // Limpiar el contenido anterior antes de agregar los eventos
        divEvento.innerHTML = '';
        //contador de eventos mostrados
        let eventosMostrados = 0;

        data.forEach(evento => {
            if (eventosMostrados < 3) { // Mostrar solo 4 eventos
                const eventContainer = document.createElement('div');
                eventContainer.classList.add('event');
                eventContainer.innerHTML = generarContenidoHTML(evento);
                divEvento.appendChild(eventContainer);
                eventosMostrados++; // Incrementar el contador
            }
        });
    } catch (error) {
        console.error('ERROR AL OBTENER EVENTO', error);
    }
}

function abrirModal(idevento, cantboletosdisponibles, precioticket) {
    document.getElementById('cantidadBoletos').value = '';
    document.getElementById('formaPago').value = '';
    document.getElementById('myModal').style.display = 'block';
}

function cerrarModal() {
    document.getElementById('myModal').style.display = 'none';
}
