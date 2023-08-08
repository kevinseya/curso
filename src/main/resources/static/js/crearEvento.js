// Call the dataTables jQuery plugin
$(document).ready(function() {
    actualizarEmailUsuario();

    //on ready
});
function actualizarEmailUsuario() {
    document.getElementById('txt-email-usuario').innerHTML = localStorage.email;
}


async function registrarEventos(idUsuarioAdmin){

  //Aqui se capta los datos ingresados en el front para el evento
    let datosEvento ={};

    datosEvento.nombre = document.getElementById('txtNombre').value;
    datosEvento.descripcion = document.getElementById('txtDescripcion').value;
    datosEvento.genero = document.getElementById('txtGenero').value;
    datosEvento.cantboletosdisponibles = document.getElementById('txtCantBoletosDisponibles').value;
    datosEvento.duracion = document.getElementById('txtDuracion').value;
    datosEvento.fecha = document.getElementById('txtFecha').value;
    datosEvento.lugar = document.getElementById('txtLugar').value;
    datosEvento.precioticket = document.getElementById('txtPrecioTicket').value;
    datosEvento.subgenero = document.getElementById('txtSubGenero').value;
    //tambien iria el id del usuario admin

    datosEvento.idusuarioadmin = localStorage.getItem("token").split(";")[1];

    const request = await fetch('api/eventos', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body:  JSON.stringify(datosEvento)
    }).then(response=>{
        if(response.ok){
            alert("EVENTO CREADO CON ÉXITO");
        }else{
            alert("ERROR AL CREAR EL EVENTO")
        }
    })

    window.location.href = 'eventos.html';
}





