// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});

async function registrarUsuariosCliente(){

    let datosCliente ={};

    datosCliente.nombre = document.getElementById('txtNombreCliente').value;
    datosCliente.apellido = document.getElementById('txtApellidoCliente').value;
    datosCliente.email = document.getElementById('txtEmailCliente').value;
    datosCliente.password = document.getElementById('txtPasswordCliente').value;


    let repetirPasswordCliente = document.getElementById('txtRepetirPasswordCliente').value;

    if (repetirPasswordCliente != datosCliente.password){
        alert('La contraseña que escribiste es diferente');
        return;
    }

    const request = await fetch('api/usuariosCliente', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body:  JSON.stringify(datosCliente)
    });

    alert("La cuenta fue creada EXITOSAMENTE!");

    window.location.href = 'loginCliente.html';
}


