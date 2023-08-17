$(document).ready(function() {
    actualizarEmailUsuario();
    obtenerResultadosBusqueda();

});

function logout(){
    localStorage.clear();
}

function actualizarEmailUsuario(){
    document.getElementById('txt-email-usuario').textContent = localStorage.email;
}



function generarContenidoHTML(evento) {
    return `
        <div class="event-container">
            <div class="image-container-img">
              <img src="img/ImagenRef.png" alt="Evento 1">
            </div>
            <div class="event-details">
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

async function enviarNombreEventoPython(nombreEvento) {
    try {
        const response = await fetch('http://localhost:5000/recibir-nombre-evento', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ nombre_evento: nombreEvento })

        });

        if (response.status === 200) {
            console.log('Nombre de evento enviado correctamente a Python.');
            const eventosRecomendados = await response.json();
            mostrarRecomendaciones(eventosRecomendados);


        } else {
            console.error('Error al enviar el nombre de evento a Python.');
        }
    } catch (error) {
        console.error('ERROR AL ENVIAR NOMBRE DE EVENTO A PYTHON', error);
    }
}


const urlParams = new URLSearchParams(window.location.search);
const searchTerm = urlParams.get('search')
async function obtenerResultadosBusqueda() {
    try {
        const response = await fetch(`api/eventosBusqueda/${searchTerm}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });

        const listaEventoEncontrados = await response.json();

        if (listaEventoEncontrados.length > 0) {
            const primerEventoEncontrado = listaEventoEncontrados[0];
            const nombrePrimerEvento = primerEventoEncontrado.nombre;

            // Enviar el nombre del primer evento a la API de Python
            enviarNombreEventoPython(nombrePrimerEvento);
        }




        const divResultados = document.querySelector('.category-container[data-tipo="Resultados"]');

        // Limpiar el contenido anterior antes de agregar los resultados de búsqueda
        divResultados.innerHTML = '';

        listaEventoEncontrados.forEach(evento => {
            const eventContainer = document.createElement('div');
            eventContainer.classList.add('event-container');
            eventContainer.innerHTML = generarContenidoHTML(evento);
            divResultados.appendChild(eventContainer);
        });
    } catch (error) {
        console.error('ERROR AL OBTENER RESULTADOS', error);
    }
}


async function mostrarRecomendaciones(eventosRecomendados) {
    const divRecomendaciones = document.querySelector('.category-container[data-tipo="Recomendaciones"]');

    // Limpiar el contenido anterior antes de agregar las recomendaciones
    divRecomendaciones.innerHTML = '';

    for (const eventoNombre of eventosRecomendados) {
        try {
            const response = await fetch(`api/eventosBusqueda/${encodeURIComponent(eventoNombre)}`, {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json'
                }
            });

            if (response.ok) {
                const evento = await response.json();
                const eventContainer = document.createElement('div');
                eventContainer.classList.add('event-container');
                eventContainer.innerHTML = generarContenidoHTML(evento[0]); // Tomar el primer evento de la lista
                divRecomendaciones.appendChild(eventContainer);
            } else {
                console.error('Error al obtener los detalles del evento:', eventoNombre);
            }
        } catch (error) {
            console.error('Error en la petición GET para obtener los detalles del evento:', error);
        }
    }
}



