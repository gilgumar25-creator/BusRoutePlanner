document.addEventListener("DOMContentLoaded", function () {
    const inputDistancia = document.getElementById("distancia");
    const inputPrecioKm = document.getElementById("preciokilometro");
    const inputPrecioViaje = document.getElementById("precioViaje");

    function calcularPrecioTotal() {
        const distancia = parseFloat(inputDistancia.value) || 0;
        const precioKm = parseFloat(inputPrecioKm.value) || 0;
        
        const total = distancia * precioKm;
        inputPrecioViaje.value = total.toFixed(2);
    }

    if (inputDistancia && inputPrecioKm) {
        inputDistancia.addEventListener("input", calcularPrecioTotal);
        inputPrecioKm.addEventListener("input", calcularPrecioTotal);
    }
});