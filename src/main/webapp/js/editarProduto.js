window.onload = async function () {
    try {
        const params = new URLSearchParams(window.location.search);
        const id = params.get("id");
        

        const response = await fetch("/api/estoque?id=" + id);
        const dados = await response.json();
        const p = dados[0];

        document.querySelector('[name="id"]').value = p.ID;
        document.querySelector('[name="codigoBarras"]').value = p.codigoBarras;
        document.querySelector('[name="nomeProduto"]').value = p.nomeProduto;
        document.querySelector('[name="fabricante"]').value = p.fabricante;
        document.querySelector('[name="Marca"]').value = p.marca;
        document.querySelector('[name="dataFabricacao"]').value = p.dataFabricacao;
        document.querySelector('[name="dataVencimento"]').value = p.datavencimento ?? "";
        document.querySelector('[name="quantidade"]').value = p.quantidade;
        document.querySelector('[name="prateleira_id"]').value = p.prateleira;
        document.querySelector('[name="EstoqueMínimo"]').value = p.estoqueMínimo;
        document.querySelector('[name="valor"]').value = p.valor;
        document.querySelector('[name="total"]').value = p.total;
        document.querySelector('[name="status"]').value = p.status;


    } catch (erro) {
        console.log("Erro ao carregar produto", erro);
    }

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
}