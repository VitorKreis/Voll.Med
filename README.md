# Voll.Med.API_Rest 
Voll.Med é uma API RESTful desenvolvida em Java utilizando Spring Boot, que simula um sistema de gerenciamento de clínica médica. O
projeto permite realizar operações de CRUD (Create, Read, Update, Delete) com médicos e pacientes, além de garantir a segurança dos endpoints.

## Funcionalidades
- Agendamento de consultas, com algum medico ou por alguma especialidade.
- Controle de Responsabilidades (Roles) para médicos, gerentes e enfermeiras.
- Documentaçao do projeto em Swagger


## Tecnologias
- Spring
- Spring Security
- Spring Data JPA
- PostgreSQL
- JUnit
- Spring DevTools

## Pré-requisitos
- Java JDK 11 ou superior
- Maven 3.6 ou superior

## Instalação
1. Clone o repositório:
     ```
       git clone https://github.com/VitorKreis/Med.Voll.Api_Rest.git
     ```
2. Navegue até o diretório do projeto:
      ```
       cd Med.voll.Api_Rest
     ```
3. Instale as dependências:
     ```
       mvn install
     ```
4. Inicia o container docker:
     ```
       docker-compose up
     ```
5. Abra a documentaçao:
     ```
       http://localhost:8090/swagger-ui/index.html#/
     ```

### Endpoints

A API expõe os seguintes *endpoints* a partir da *base URL* `localhost:8090`:

`/medico`
* `GET /medico - Acessível por qualquer usuário (ANY)`
* `GET /medico/:id - Acessível por qualquer usuário (ANY)`
* `POST /medico - Acessível para usuários com as roles MEDICO e GERENTE`
* `PUT /medico/:id - Acessível para usuários com as roles MEDICO e GERENTE`
* `DELETE /medico/:id - Acessível apenas para usuários com a role GERENTE`

`/paciente`
* `GET /paciente - Acessível por qualquer usuário (ANY)`
* `GET /paciente/:id - Acessível por qualquer usuário (ANY)`
* `POST /paciente - Acessível para usuários com as roles MEDICO, GERENTE e ENFERMEIRA`
* `PUT /paciente/:id - Acessível para usuários com as roles MEDICO, GERENTE e ENFERMEIRA`
* `DELETE /paciente/:id - Acessível para usuários com as roles MEDICO, GERENTE e ENFERMEIRA`

`/consultas`
* `GET /consultas - Acessível por qualquer usuário (ANY)`
* `GET /consultas/:id - Acessível por qualquer usuário (ANY)`
* `POST /consultas - Acessível para usuários com as roles MEDICO, GERENTE e ENFERMEIRA`
* `DELETE /consultas/:id - Acessível apenas para usuários com a role GERENTE`
