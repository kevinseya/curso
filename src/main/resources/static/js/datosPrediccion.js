
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
        alert(JSON.stringify(data));
    }).catch(error =>{
        alert("Error", error);
    })

}




