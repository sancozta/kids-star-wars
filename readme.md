### Projeto Api Star Wars Rest - MongoDB

#### Pré-Requisitos

- Java 11
- Maven 3.2+
- Docker

#### Docker Compose

- Para subir a aplicação com o docker compose, você precisa ter instalado o docker na sua máquina. [Você consegue realizar o download aqui](https://www.docker.com/get-started).

- Verifique se tudo esta ok com o comando:


		mvnw clean install


- Depois faça o build utilizando o docker compose:


		docker-compose build


- Depois basta subir a aplicação com o comando:


		docker-compose up


- Caso queira derrubar a aplicação executar o comando:


		docker-compose down


#### Documentação das Rotas

- A documentação das rotas encontra-se em https://documenter.getpostman.com/view/3795153/TzXwEdto nesse link você poderá entender como utilizar as rotas, como também baixar uma collection caso deseje.


#### Testes Unitários

- Para executar os testes, certifique-se de que tenha executado **mvnw clean install**, naturamente isso já deve executar os testes, porém caso queira executá-los novamente basta rodar o comando abaixo:

		mvnw test


#### MongoDB Apenas

- Para subir apenas o mongoDB e executar a aplicação em uma IDE, siga os passos:

- No arquivo **application.properties** alterar **spring.data.mongodb.host** para **localhost**

- Executar comando para subir o mongoDB:
		

		docker run --name io-db -p 27017:27017 -d mongo

- Depois disso basta subir a aplicação por uma IDE de sua preferência (Spring Tool Suite (STS), IntelliJ IDEA).

