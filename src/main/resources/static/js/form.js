document.addEventListener("DOMContentLoaded", () => {
    const userForm = document.getElementById("user-form");
    const backButton = document.getElementById("back-button");

    backButton.addEventListener("click", () => {
        window.location.href = "list.html"; // Voltar para a lista
    });

    userForm.addEventListener("submit", event => {
        event.preventDefault();

        const user = {
            nome: document.getElementById("name").value,
            email: document.getElementById("email").value,
            senha: document.getElementById("password").value,
            permissao: document.getElementById("roles").value.split(",").map(role => role.trim())
        };

        fetch("http://localhost:8080/usuarios", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(user)
        })
        .then(response => {
            if (!response.ok) {
                return response.json().then(error => {
                    console.error("Erro do servidor:", error); // Logar o erro detalhado
                    throw new Error(error.message || "Erro ao salvar usuário");
                });
            }
            return response.json();
        })
        .then(data => {
            alert("Usuário salvo com sucesso!");
            window.location.href = "list.html";
        })
        .catch(error => {
            console.error("Erro ao salvar usuário:", error);
            alert("Erro ao salvar usuário: " + error.message);
        });
    });
});