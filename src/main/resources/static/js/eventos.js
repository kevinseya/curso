// Call the dataTables jQuery plugin
$(document).ready(function() {
    actualizarEmailUsuario();
//carga la funcion de listar los usuarios a la tabla en la página de administrador
    cargarEventos();
//concatena la tabla con la lista
    $('#eventos').DataTable();
    // actualiza por medio de la funcion en la parte superior el nombre del usuarioadmin logeado

});
//funcion para actualizar nombre admin logeado
function actualizarEmailUsuario(){
    document.getElementById('txt-email-usuario').outerHTML = localStorage.email;
}


//Aquí carga la lista de los usuarios clientes
async function cargarEventos(){
//api para conectar la parte de JAVA con JavaScript
    const request = await fetch('api/eventos', {
        method: 'GET',
        headers: getHeaders()
    });
    //reques en forma json de los usuarios
    const eventos = await request.json();
// decalramos variables en javascript de tal manera que nos permita traer los datos de la lista
    // y a su vez mostrar estos en el HTML del front
    let listadoEventosHTML = '';
    //mediante un for each se llena los objetos creados con objetos de la base de datos
    for(let evento of eventos){
        //funcionalidad del botonEliminar para eliminar usuarios
        let botonEliminar = '<a href="#" onclick="eliminarEvento(' + evento.idevento + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

        //se indica en cada apartado de la tabla los diferentes atributos de la clase Cliente que a su vez estan en la BD usuario cliente

        let eventoHTML = '<tr><td>'+evento.idevento+'</td><td>'+evento.nombre+'</td><td>'+evento.genero+'</td><td>' +
            ''+evento.subgenero+'</td><td>'+evento.cantboletosdisponibles+'</td><td>'+evento.precioticket+'</td><td>'+
            evento.duracion+'</td><td>'+evento.fecha+'</td><td>'+evento.lugar+'</td><td>'+botonEliminar+'</td>' +
            '</tr>';
        //se llena el listado con cada usuario cliente creado
        listadoEventosHTML += eventoHTML;
    }
    //Se concatena la tabla con este listado de clientes-
    document.querySelector('#eventos tbody').outerHTML = listadoEventosHTML;

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
async function eliminarEvento(idevento){

    if(!confirm('¿Desea eliminar este evento?')){
        return;
    }
//api para conectar con el http de usuarios y a la vez en especifico del usuario por medio de su id
    const request = await fetch('api/eventos/' + idevento, {
        method: 'DELETE',
        headers: getHeaders()
    });
    location.reload();

}
