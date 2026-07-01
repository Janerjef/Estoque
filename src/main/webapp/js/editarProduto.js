window.onload = async function () {

    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");


    const response = await fetch("/api/estoque?id=" + id);
    const lista = await response.json();
    const p = lista[0];

    // preenche cada campo
    document.getElementById("campoid").value = p.ID;
    document.getElementById("nomeProduto").value = p.nomeProduto;
    document.getElementById("codigoBarras").value = p.codigoBarras;
    document.getElementById("fabricante").value = p.fabricante;
    document.getElementById("Marca").value = p.Marca;
    document.getElementById("dataFabricacao").value = p.dataFabricacao;
    document.getElementById("dataVencimento").value = p.datavencimento;
    document.getElementById("quantidade").value = p.quantidade;
    document.getElementById("Prateleira").value = p.prateleira;
    document.getElementById("EstoqueMínimo").value = p.estoqueMínimo;
    document.getElementById("valor").value = p.valor;
}