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
---

## 🚀 Gerenciamento de Migrações & Scripts DDL (Questão 2)

Para garantir a evolução controlada do banco de dados e atender aos requisitos de isolamento do ORM, o projeto foi estruturado para utilizar uma ferramenta de versionamento de banco de dados (Flyway), mitigando alterações automáticas imprevisíveis em ambiente de produção.

### 1. Inibição do Schema Automático do Hibernate
Em total conformidade com o enunciado, o comportamento de geração automática do Hibernate é desativado através da seguinte propriedade de configuração em ambiente produtivo:
```properties
spring.jpa.hibernate.ddl-auto=none
http://localhost:8080/api/livros?page=0&size=2


**Exemplo de Resposta (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpX...RsmkJoNXypXTPEdQGbCNm18"
}
---

## 🗺️ Mapeamento ORM & Modelo Relacional (Questão 1)

A arquitetura do domínio foi inteiramente construída utilizando **JPA / Hibernate** para mapear o banco de dados relacional a partir do diagrama fornecido. Os relacionamentos e cardinalidades foram implementados de forma bidirecional respeitando os seguintes critérios:

### 1. Relação Um para Um (1:1)
* **Entidades:** `Livro` ↔ `ResumoLivro`
* **Implementação:** A entidade `Livro` atua como dona do relacionamento utilizando a anotação `@OneToOne` conjugada com `@JoinColumn(name = "resumo_id")`. A entidade `ResumoLivro` mapeia a via inversa usando `mappedBy = "resumoLivro"`.
* **Resultado no Banco:** Geração de uma chave estrangeira única na tabela de livros.

### 2. Relação Um para Muitos (1:N)
* **Entidades:** `Editora` ↔ `Livro`
* **Implementação:** Cada `Livro` possui uma única `Editora` mapeada por `@ManyToOne` com `@JoinColumn(name = "editora_id")`. Por sua vez, `Editora` possui uma lista de livros anotada com `@OneToMany(mappedBy = "editora")`.
* **Resultado no Banco:** Criação da chave estrangeira `editora_id` dentro da tabela de livros para integridade referencial.

### 3. Relação Muitos para Muitos (N:M)
* **Entidades:** `Livro` ↔ `Autor`
* **Implementação:** Relacionamento gerenciado através da anotação `@ManyToMany` no atributo `autores` de `Livro`. A tabela intermédia e associativa foi customizada explicitamente no código através da anotação:
  ```java
  @JoinTable(
      name = "livro_autor",
      joinColumns = @JoinColumn(name = "livro_id"),
      inverseJoinColumns = @JoinColumn(name = "autor_id")
  )
