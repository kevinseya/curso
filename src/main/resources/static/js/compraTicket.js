
function abrirModal(idevento, cantboletosdisponibles, precioticket) {
    document.getElementById('cantidadBoletos').value = '';
    document.getElementById('formaPago').value = '';
    document.getElementById('myModal').style.display = 'block';
}

function cerrarModal() {
    document.getElementById('myModal').style.display = 'none';
}
