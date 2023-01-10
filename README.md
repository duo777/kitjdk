# oxefood-final
Projeto utilizado como exemplo para a disciplina de desenvolvimento para WEB IV do curso de TADS do IFPE Campus Jaboatão dos Guararapes.

Pré-requisitos para o ambiente:

 - Instalar o JDK 11
 - Instalar o Git
 - Instalar o Docker / Docker-compose


Para rodar o projeto siga os passos abaixo e execute os comandos:

1) Baixe o projeto do repositório git, exemplo:

    git clone https://github.com/robertoalencar/oxefood-final.git

2) Entre na pasta do projeto e execute o comando abaixo para levantar o banco de dados:

    docker-compose up -d

3) Ainda na pasta do projeto, execute o comando abaixo:

    ./mvnw spring-boot:run
