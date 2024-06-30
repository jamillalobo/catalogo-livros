# Catálogo de Livros

## Descrição

Este é um projeto de uma aplicação Spring Boot para praticar manipulação de JSON e persistência de dados. A aplicação oferece funcionalidades para gerenciamento de livros e autores.

## Funcionalidades

1. **Buscar livro pelo título**: Permite consumir um livro da API Gutendex e cadastrar pelo título.
2. **Listar livros registrados**: Exibe todos os livros cadastrados no sistema.
3. **Listar autores registrados**: Exibe todos os autores cadastrados no sistema.
4. **Listar autores vivos em um determinado ano**: Permite listar os autores que estavam vivos em um ano específico.
5. **Listar livros em um determinado idioma**: Permite listar os livros escritos em um idioma específico.

## Tecnologias Utilizadas

- Spring Framework
- Spring Data JPA
- PostgreSQL
- Jackson Databind
- API Gutendex

## Configuração do Ambiente

Para testar a aplicação, você precisará configurar as variáveis de ambiente para conexão com o banco de dados PostgreSQL. Adicione as seguintes variáveis de ambiente em seu arquivo de configuração (`application.properties` ou `application.yml`):

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver
hibernate.dialect=org.hibernate.dialect.HSQLDialect

spring.jpa.hibernate.ddl-auto=update
```

Certifique-se de substituir `${DB_HOST}`, `${DB_NAME}`, `${DB_USER}`, e `${DB_PASSWORD}` pelos valores corretos do seu ambiente de desenvolvimento.

## Como executar aplicação

1. **Clone o repositório**:
    ```sh
    git clone https://github.com/seu-usuario/seu-repositorio.git
    ```
2. **Acesse o diretório do projeto**:
    ```sh
    cd seu-repositorio
    ```
3. **Configure as variáveis de ambiente** conforme mencionado acima.
4. **Execute a aplicação via terminal**:
    ```sh
    ./mvnw spring-boot:run
    ```

## Contribuições
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.

---

Desenvolvido por Jamilla Lobo <♡︎/>
