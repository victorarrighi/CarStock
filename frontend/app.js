const apiUrl = 'http://localhost:8080/carros';

function listarCarros() {
    fetch(apiUrl)
        .then(res => res.json())
        .then(carros => {
            const lista = document.getElementById('lista-carros');
            lista.innerHTML = '';
            carros.forEach(carro => {
                const item = document.createElement('li');
                item.textContent = `${carro.marca} ${carro.modelo} - R$${carro.preco}`;

                const btnDel = document.createElement('button');
                btnDel.textContent = 'Excluir';
                btnDel.onclick = () => deleteCarro(carro.id);

                const btnEdit = document.createElement('button');
                btnEdit.textContent = 'Editar';
                btnEdit.onclick = () => updateCarro(carro.id);

                item.appendChild(btnDel);
                item.appendChild(btnEdit);

                lista.appendChild(item);
            });
        });
}

function createCarro() {
    const modelo = document.getElementById('modelo').value;
    const marca = document.getElementById('marca').value;
    const preco = parseFloat(document.getElementById('preco').value);

    const carro = { modelo, marca, preco };

    fetch(apiUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(carro)
    })
    .then(() => listarCarros());
}

function deleteCarro(id) {
    fetch(`${apiUrl}/${id}`, { method: 'DELETE' })
        .then(() => listarCarros());
}

function updateCarro(id) {
    const novoModelo = prompt("Novo modelo:");
    const novaMarca = prompt("Nova marca:");
    const novoPreco = parseFloat(prompt("Novo preço:"));

    const carro = { modelo: novoModelo, marca: novaMarca, preco: novoPreco };

    fetch(`${apiUrl}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(carro)
    })
    .then(() => listarCarros());
}

listarCarros();
