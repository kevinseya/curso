// Call the dataTables jQuery plugin
$(document).ready(function() {
//carga la funcion de listar los usuarios a la tabla en la página de administrador
  cargarUsuariosCliente();
//concatena la tabla con la lista
  $('#usuarios').DataTable();
  // actualiza por medio de la funcion en la parte superior el nombre del usuarioadmin logeado
  actualizarEmailUsuario();
});
//funcion para actualizar nombre admin logeado
function actualizarEmailUsuario(){
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}
//Esta linea es de funcion para cargar los usuarios administradores
/*
async function cargarUsuarios(){

    const request = await fetch('api/usuarios', {
      method: 'GET',
      headers: getHeaders()
    });
    const usuarios = await request.json();

    let listadoHTML = '';
    for(let usuario of usuarios){
        let botonEliminar = '<a href="#" onclick="eliminarUsuario(' + usuario.idusuarioadmin + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        let telfTexto = usuario.telf == null ? '_' : usuario.telf;
        let usuarioHTML = '<tr><td>'+usuario.idusuarioadmin+'</td><td>'+usuario.nombre+' '+usuario.apellido+'</td><td>' +
                                ''+usuario.email+'</td><td>'+telfTexto
                                +'</td><td>'+botonEliminar+'</td></tr>';
        listadoHTML += usuarioHTML;
    }
    //console.log(usuarios);

    document.querySelector('#usuarios tbody').outerHTML = listadoHTML;

}

*/

//Aquí carga la lista de los usuarios clientes
async function cargarUsuariosCliente(){
//api para conectar la parte de JAVA con JavaScript
    const request = await fetch('api/usuariosCliente', {
        method: 'GET',
        headers: getHeaders()
    });
    //reques en forma json de los usuarios
    const usuariosCliente = await request.json();
// decalramos variables en javascript de tal manera que nos permita traer los datos de la lista
    // y a su vez mostrar estos en el HTML del front
    let listadoClientesHTML = '';
    //mediante un for each se llena los objetos creados con objetos de la base de datos
    for(let usuarioCliente of usuariosCliente){
        //funcionalidad del botonEliminar para eliminar usuarios
        let botonEliminar = '<a href="#" onclick="eliminarUsuarioCliente(' + usuarioCliente.idusuariocliente + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
        //funcionalidad para que el apartado que este nulo se indique como '-'
        let telfTexto = usuarioCliente.telf == null ? '_' : usuarioCliente.telf;
        //se indica en cada apartado de la tabla los diferentes atributos de la clase Cliente que a su vez estan en la BD usuario cliente
        let usuarioClienteHTML = '<tr><td>'+usuarioCliente.idusuariocliente+'</td><td>'+usuarioCliente.nombre+' '+usuarioCliente.apellido+'</td><td>' +
            ''+usuarioCliente.email+'</td><td>'+telfTexto
            +'</td>' +
            //'<td>'+botonEliminar+'</td>' +
            '</tr>';
        //se llena el listado con cada usuario cliente creado
        listadoClientesHTML += usuarioClienteHTML;
    }
    //Se concatena la tabla con este listado de clientes-
    document.querySelector('#usuarios tbody').outerHTML = listadoClientesHTML;

}

//utilizamos una funcion de cabeceras donde utilizaremos el token como metodo de autentificación para nuestro usuario
//de tal manera que nos permitirá acceder a la información de la lista de usuariosClientes
function getHeaders() {
    return {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        'Authorization': localStorage.token
    };
}
//aqui esta la funcion de eliminar usuarios administradores
/*async function eliminarUsuario(idusuarioadmin){

    if(!confirm('¿Desea eliminar este usuario?')){
        return;
    }
//api para conectar con el http de usuarios y a la vez en especifico del usuario por medio de su id
    const request = await fetch('api/usuarios/' + idusuarioadmin, {
        method: 'DELETE',
        headers: getHeaders()
    });
    location.reload();

}
 */


//la función de eliminar usuarios clientes
async function eliminarUsuarioCliente(idusuariocliente){

    if(!confirm('¿Desea eliminar este usuario?')){
        return;
    }
//api para conectar con el http de usuarios y a la vez en especifico del usuario por medio de su id
    const request = await fetch('api/usuariosCliente/' + idusuariocliente, {
        method: 'DELETE',
        headers: getHeaders()
    });
    location.reload();

}
