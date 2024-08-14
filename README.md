# Voll.Med.API_Rest 
Voll.Med é uma API RESTful desenvolvida em Java utilizando Spring Boot, que simula um sistema de gerenciamento de clínica médica. O
projeto permite realizar operações de CRUD (Create, Read, Update, Delete) com médicos e pacientes, além de garantir a segurança dos endpoints.

## Funcionalidades
- Agendamento de consultas, com algum medico ou por alguma especialidade.
- Controle de Responsabilidades - em andamento


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

### Endpoints

A API expõe os seguintes *endpoints* a partir da *base URL* `localhost:8090`:

`/medico`
* `GET /medico`
* `GET /medico/:id`
* `POST /medico`
* `PUT /medico/:id`
* `DELETE /medico/:id`

`/paciente`
* `GET /paciente`
* `GET /paciente/:id`
* `POST /paciente`
* `PUT /paciente/:id`
* `DELETE /paciente/:id`

`/consultas`
* `GET /consultas`
* `GET /consultas/:id`
* `POST /consultas`
* `DELETE /consultas/:id`
