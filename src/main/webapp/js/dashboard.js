async function carregarEstoque(){
    try {
        const response = await fetch("/api/estoque");
        const dados = await response.json();
        const tabela = document.getElementById("corpoTabela");
        tabela.innerHTML= "";

        dados.forEach(item => {
            const linha =`
          <tr>
              <td>${item.codigoBarras}</td>    
              <td>${item.nomeProduto}</td>
              <td>${item.fabricante}</td>
              <td>${item.marca}</td>
              <td>${item.quantidade}</td>
              <td>${item.valor}</td>
              <td>${item.total}</td>
          </tr>    
          `;
            tabela.innerHTML += linha;
    })
    }catch(erro){
        console.log("Erro ao cadastrar os produtos", erro);
    }
}


async function carregarResumo(){
    try{
        const response = await fetch("/api/resumo");
        const dados = await response.json();

        document.getElementById("cardEntrada").innerHTML = dados.entrada ?? 0;
        document.getElementById("cardSaida").innerHTML = dados.saida ?? 0;
        document.getElementById("cardTotal").innerHTML = dados.total ?? 0;
    }catch(erro){
        console.log("Erro da consulta dos daods", erro);

    }
}
window.onload = () => {
    carregarEstoque();
    carregarResumo();
}