# JAX-RS | AngularJs #
Foi desenvolvido uma aplicação Web com o objetivo de demonstrar o gerenciamento de veículos. Para o back-end temos uma aplicação JavaEE baseada em Web Services no padrão RESTful JAX-RS. O front-end foi baseado em AngularJs, com bootstrap e HTML5.

### Dependências de instalação ###
As seguintes dependências são necessárias:

 - Java 8
 - bower
 - npm
 - nodejs
 - gulp
 - maven 3

### Instalando dependências frontend ###

Após clonar o repositório, o comando a seguir instala as dependências Javascript:

    bower install

### Criando e iniciando o servidor ###

Para o back-end execute o seguinte comando na pasta raiz do repositório:

    mvn clean install tomcat7:run-war -Dspring.profiles.active=test
    
Em seguida,  execute o seguinte comando na pasta raiz do repositório para a iniciar o front-end:

    gulp server

Depois que o servidor for iniciado, o aplicativo estará acessível no seguinte URL:

    http://localhost:3000/carmanager

### Visão geral do frontend ###
Aplicação web se basea em Single Page Application (SPA) utilizando AngularJs (Material), bootstrap, Html5. Seguindo as boas práticas aplicadas.
##### Screenshot #####
-
##### Estrutura #####
- Módulo
- Controller
- Factory
- Rota

### Visão geral do back-end ###
O backend foi desenvolvido em Java 8, JAX-RS (Jersey) e JPA. Usando a abordagem DDD, se baseando em domínio, serviços, repositórios e DTOs para transferência de dados front-end / back-end. Juntamente com TDD, se obteve o software orientado a testes tendo uma cobertura abrangente. Para log foi utilizado Log4j.

#### API REST ####

A API REST do backend é composta:
##### User Service #####

URL | Verbo | Descrição
-------------- | ------------- | -------------
/veiculos | GET | Retorna todos os veículos
/veiculos/find | GET | Retorna os veículos de acordo com o termo passado parâmetro q
/veiculos/{id} | GET | Retorna os detalhes do veículo
/veiculos | POST | Adiciona um novo veículo
/veiculos/{id} | PUT | Atualiza os dados de um veículo
/veiculos/{id} | PATCH | Atualiza apenas alguns dados do veículo
/veiculos/{id} | DELETE | Apaga o veículo
