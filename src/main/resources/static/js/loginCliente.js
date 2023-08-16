$(document).ready(function() {
    //on ready
});

async function iniciarSesionCliente(){

    let datosCliente ={};

    datosCliente.email = document.getElementById('txtEmailCliente').value;
    datosCliente.password = document.getElementById('txtPasswordCliente').value;


    const request = await fetch('api/loginCliente', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body:  JSON.stringify(datosCliente)
    });

    const respuesta = await request.text();
    if(respuesta != "FAIL"){
        Textoseparado = respuesta.split(';');
        localStorage.token = Textoseparado[0];
        localStorage.id = Textoseparado[1];
        localStorage.token = respuesta;
        localStorage.email = datosCliente.email;

        window.location.href = 'index.html'
    }else{
        alert("las credenciales son incorrectas. Intente nuevamente")
    }

}