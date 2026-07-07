window.onload = async function () {
    const inputValor = document.querySelector('input[name="valor"]');
    const inputQtd = document.querySelector('input[name="quantidade"]');
    const inputTotal = document.querySelector('input[name="total"]');
    const selectPrateleira = document.querySelector('#prateleira_id');

    if (inputValor && inputQtd && inputTotal) {
        function calcular() {
            const valor = parseFloat(inputValor.value) || 0;
            const quantidade = parseFloat(inputQtd.value) || 0;
            inputTotal.value = (valor * quantidade).toFixed(2);
        }

        inputValor.addEventListener("input", calcular);
        inputQtd.addEventListener("input", calcular);
    }

    try {
        const response = await fetch("/Prateleira");
        const prateleiras = await response.json();

        prateleiras.forEach(prateleira => {
            const option = document.createElement("option");

            option.value = prateleira.nome;
            option.textContent = prateleira.nome;

            selectPrateleira.appendChild(option);
        });

    } catch (erro) {
        console.log("Erro ao carregar prateleiras", erro);
    }
};