# 📚 API Bibliográfica - Terceira Avaliação PW

Este projeto consiste em uma API RESTful desenvolvida com Spring Boot para o gerenciamento de um catálogo de livros, editoras e autores, desenvolvida para a disciplina de Programação Web.

---

## 🚀 Endpoints Principais de Autenticação e Cadastro

Abaixo estão os detalhes das rotas para obter o token de acesso e realizar a inserção de novos livros no sistema.

### 1. Obter Token de Autenticação (Login)
Gera o Token JWT necessário para efetuar operações de alteração no banco de dados.

* **URL:** `http://localhost:8080/api/auth/login`
* **Método HTTP:** `POST`
* **Autenticação:** Nenhuma (Pública)
* **Corpo (Body):** Não exige corpo (gera credenciais automáticas de administrador)
* **Exemplo de URL com parâmetros:**
```http
http://localhost:8080/api/livros?page=0&size=2


**Exemplo de Resposta (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpX...RsmkJoNXypXTPEdQGbCNm18"
}
