window.onload = function () {
    const inputValor = document.querySelector('input[name="valor"]');
    const inputQtd   = document.querySelector('input[name="quantidade"]');
    const inputTotal = document.querySelector('input[name="total"]');

    if (inputValor && inputQtd && inputTotal) {
        function calcular() {
            const valor = parseFloat(inputValor.value) || 0;
            const quantidade = parseFloat(inputQtd.value) || 0;
            inputTotal.value = (valor * quantidade).toFixed(2);
        }

        inputValor.addEventListener("input", calcular);
        inputQtd.addEventListener("input", calcular);
    }
};