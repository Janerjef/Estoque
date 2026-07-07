window.onload = async function () {
    try {
        const responsePrateleiras = await fetch("/Prateleira");
        const prateleiras = await responsePrateleiras.json();

        const responseProdutos = await fetch("/api/estoque");
        const produtos = await responseProdutos.json();

        const container = document.getElementById("container-prateleiras");
        const msgVazio = document.getElementById("mensagem-vazio");

        container.innerHTML = "";

        if (!prateleiras || prateleiras.length === 0) {
            msgVazio.style.display = "block";
            return;
        }

        msgVazio.style.display = "none";

        prateleiras.forEach(prateleira => {
            const produtosDaPrateleira = produtos.filter(produto =>
                produto.prateleira &&
                produto.prateleira.trim().toLowerCase() === prateleira.nome.trim().toLowerCase()
            );

            const itens = produtosDaPrateleira.map(produto => {
                const precisaRepor = produto.quantidade <= produto.estoqueMínimo;
                const classeQtd = precisaRepor ? "produto-quantidade repor" : "produto-quantidade";
                const labelQtd = precisaRepor
                    ? `${produto.quantidade} Repor`
                    : `Qtd: ${produto.quantidade}`;

                return `
                    <a class="produto-item" href="/pages/editarProduto.html?id=${produto.ID}">
                        <span class="produto-nome" title="${produto.nomeProduto}">${produto.nomeProduto}</span>
                        <span class="${classeQtd}">${labelQtd}</span>
                    </a>
                `;
            }).join("");

            container.innerHTML += `
                <div class="card-prateleira">
                    <div class="card-header-prateleira">
                        <span class="icone">📦</span>
                        <h3>${prateleira.nome}</h3>
                    </div>

                    <div class="card-body-prateleira">
                        ${
                itens || `<p class="produto-item">Nenhum produto nessa prateleira</p>`
            }
                    </div>

                    <div class="card-footer-prateleira">
                        ${produtosDaPrateleira.length} produto${produtosDaPrateleira.length !== 1 ? "s" : ""}
                    </div>
                </div>
            `;
        });

    } catch (erro) {
        console.log("Erro ao carregar prateleiras", erro);
    }
}