document.addEventListener("DOMContentLoaded", () => {
    const userListDiv = document.getElementById("user-list");
    const addUserButton = document.getElementById("add-user-button");

    addUserButton.addEventListener("click", () => {
        window.location.href = "form.html"; // Navegar para a página de formulário
    });

    fetch("http://localhost:8080/usuarios")
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar usuários");
            return response.json();
        })
        .then(users => {
            if (users.length === 0) {
                userListDiv.innerHTML = "<p>Não há usuários cadastrados</p>";
            } else {
                userListDiv.innerHTML = users.map(user => `
                    <div class="user-item">
                        <p><strong>Nome:</strong> ${user.nome}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Permissões:</strong> ${user.permissao.join(", ")}</p>
                    </div>
                `).join("");
            }
        })
        .catch(error => {
            console.error("Erro ao carregar usuários:", error);
            userListDiv.innerHTML = "<p>Erro ao carregar usuários.</p>";
        });
});
