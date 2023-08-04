// Call the dataTables jQuery plugin
$(document).ready(function() {

    cargarUsuariosCliente();

    $('#usuariosCliente').DataTable();
    actualizarEmailUsuarioCliente();
});

function actualizarEmailUsuarioCliente(){
    document.getElementById('txt-email-usuarioCliente').outerHTML = localStorage.email;
}
async function cargarUsuariosCliente(){

    const request = await fetch('api/usuariosCliente', {
        method: 'GET',
        headers: getHeaders()
    });
    const usuariosCliente = await request.json();
//corregirLasEntradashtml

    let listadoClientesHTML = '';
    for(let usuarioCliente of usuariosCliente){
        let botonEliminar = '<a href="#" onclick="eliminarUsuarioCliente(' + usuarioCliente.idusuariocliente + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let telfTexto = usuarioCliente.telf == null ? '_' : usuarioCliente.telf;
        let usuarioClienteHTML = '<tr><td>'+usuarioCliente.idusuariocliente+'</td><td>'+usuarioCliente.nombre+' '+usuarioCliente.apellido+'</td><td>' +
            ''+usuarioCliente.email+'</td><td>'+telfTexto
            +'</td><td>'+botonEliminar+'</td></tr>';
        listadoClientesHTML += usuarioClienteHTML;
    }
    //console.log(usuarios);
//#usuarios corregir
    document.querySelector('#usuariosCliente tbody').outerHTML = listadoClientesHTML;

}

function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}

async function eliminarUsuarioCliente(idusuariocliente){

    if(!confirm('¿Desea eliminar este usuario?')){
        return;
    }

    const request = await fetch('api/usuariosCliente/' + idusuariocliente, {
        method: 'DELETE',
        headers: getHeaders()
    });
    location.reload();

}
