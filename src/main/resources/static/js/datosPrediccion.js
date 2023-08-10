
$(document).ready(function() {
    actualizarEmailUsuario();


});

function actualizarEmailUsuario(){
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}
async function cargarDatosPrediccion() {

    var generoPrediccion = document.getElementById('txtGeneroPrediccion').value;
    var fechaPrediccion = document.getElementById('txtFechaPrediccion').value;
    var duracionPrediccion = document.getElementById('txtDuracionPrediccion').value;


const datosPrediccion ={
    "genero" : generoPrediccion.toString(),
    "duracion" : duracionPrediccion.toString(),
    "diaSemana" : fechaPrediccion.toString()
}

fetch('http://localhost:5000/api/modeloPrediccion',{
    method: 'POST',
    headers: {
        'Content-Type':'application/json',
    },
    body: JSON.stringify(datosPrediccion)
}).then(response => response.json())
    .then(data=>{
        alert("Predicción Realizada");


        var prediccionAsistentes = data.prediccion_asistentes;
        var prediccionPrecioTicket = parseFloat(data.prediccion_precio).toFixed(2);

        document.getElementById('prediccionAsistentes').value = prediccionAsistentes;
        document.getElementById('prediccionPrecio').value = prediccionPrecioTicket;
        var btnAceptar = document.getElementById('botonPrediccionAceptar');
        var btnCancelar = document.getElementById('botonPrediccionCancelar');



        btnAceptar.addEventListener('click', function(){

            window.location.href = `crear_evento.html?prediccionAsistentes=${prediccionAsistentes}&prediccionPrecio=${prediccionPrecioTicket}&generoPrediccion=${generoPrediccion}&fechaPrediccion=${fechaPrediccion}&duracionPrediccion=${duracionPrediccion}`;

        });


        btnCancelar.addEventListener('click', function(){
            document.getElementById('prediccionAsistentes').value = '';
            document.getElementById('prediccionPrecio').value = '';
            document.getElementById('txtFechaPrediccion').value ='';
            document.getElementById('txtDuracionPrediccion').value='';

        });

        var divResultados = document.getElementById('resultadoPrediccionTOTAL');
        divResultados.scrollIntoView({behavior: 'smooth', block: 'start'});

    }).catch(error =>{
        alert("Error", error);
    })




}




