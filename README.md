MovieFlix 🎬

📌 Visão Geral do Projeto

MovieFlix é uma API desenvolvida em Java com Spring Boot para o gerenciamento de um catálogo de filmes.
O sistema permite o cadastro e a organização de filmes, categorias e serviços de streaming, além da autenticação de usuários com JWT.

✨ Funcionalidades

Cadastro de filmes com informações como nome, descrição, data de lançamento e avaliação.

Gerenciamento de categorias para organizar os filmes.

Cadastro de serviços de streaming associados aos filmes.

Sistema de autenticação de usuários com JWT.

Documentação automatizada com OpenAPI Swagger.

🛠️ Tecnologias Utilizadas

Java 17 → Linguagem principal do projeto.

Spring Boot 3 → Framework para desenvolvimento da API.

Spring Web → Criação de endpoints RESTful.

Spring Security + JWT → Autenticação e segurança da API.

Spring Data JPA → Gerenciamento do banco de dados.

PostgreSQL → Banco de dados relacional.

Docker → Utilizado para rodar o banco de dados PostgreSQL em um contêiner, facilitando a configuração e execução do ambiente.

Flyway → Controle de versões do banco de dados.

Lombok → Redução de boilerplate no código.

Swagger (OpenAPI) → Documentação interativa da API.

Git & GitHub → Controle de versão e hospedagem do repositório.

🗄️ Design do Banco de Dados

A API MovieFlix segue um modelo relacional com as seguintes entidades:

Movie → Contém os dados do filme: id, nome, descrição, data de lançamento, avaliação, created_at, updated_at.

Category → Representa as categorias dos filmes.

Streaming → Representa os serviços de streaming onde os filmes estão disponíveis.

User → Armazena informações dos usuários autenticados.

As relações entre as tabelas permitem que um filme tenha múltiplas categorias e esteja disponível em diferentes serviços de streaming.

🚀 Como Executar o Projeto

🌐 Acessar a API: Acesse a documentação interativa via Swagger:🔗 http://localhost:8080/swagger-ui.html

📜 Licença: Este projeto é de código aberto e está disponível sob a licença MIT.

👨‍💻 Autor: Desenvolvido por Arthur Vasconcelos 🎬🚀

🤝 Contribuição: Sinta-se à vontade para contribuir com melhorias, relatando issues ou abrindo pull requests.
