window.onload = async function () {
    try {
        const response = await fetch("/api/estoque");
        const produtos = await response.json();

        const container = document.getElementById("container-prateleiras");
        const msgVazio = document.getElementById("mensagem-vazio");

        if (!produtos || produtos.length === 0) {
            msgVazio.style.display = "block";
            return;
        }

        // agrupa os produtos por prateleira
        const grupos = {};
        produtos.forEach(p => {
            const chave = p.prateleira && p.prateleira.trim() !== ""
                ? p.prateleira
                : "Sem prateleira";
            if (!grupos[chave]) grupos[chave] = [];
            grupos[chave].push(p);
        });

        // renderiza um card por prateleira
        for (const prateleira in grupos) {
            const lista = grupos[prateleira];
            const totalItens = lista.length;

            const itens = lista.map(p => {
                const precisaRepor = p.quantidade <= p.estoqueMínimo;
                const classeQtd = precisaRepor ? "produto-quantidade repor" : "produto-quantidade";
                const labelQtd = precisaRepor
                    ? `${p.quantidade} ⚠ Repor`
                    : `Qtd: ${p.quantidade}`;

                return `
                    <a class="produto-item" href="/pages/editarProduto.html?id=${p.ID}">
                    <span class="produto-nome" title="${p.nomeProduto}">${p.nomeProduto}</span>
                    <span class="${classeQtd}">${labelQtd}</span>
                    </a>
                `;
            }).join("");

            container.innerHTML += `
                <div class="card-prateleira">
                    <div class="card-header-prateleira">
                        <span class="icone">📦</span>
                        <h3>${prateleira}</h3>
                    </div>
                    <div class="card-body-prateleira">
                        ${itens}
                    </div>
                    <div class="card-footer-prateleira">
                        ${totalItens} produto${totalItens !== 1 ? "s" : ""}
                    </div>
                </div>
            `;
        }

    } catch (erro) {
        console.log("Erro ao carregar prateleiras", erro);
    }
}
