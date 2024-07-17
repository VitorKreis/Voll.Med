# Voll.Med

Uma API Rest, que simula um sistema de gerenciamento de clínica médica. 
O projeto permite realizar operações de CRUD (Create, Read, Update, Delete) com médicos e pacientes, e agendar consultas com medicos especificos além de garantir a segurança dos endpoints

## Funcionalidades
- CRUD completo de medicos e pacientes
- Controle de agendamentos de consultas, com médicos específicos ou por especialidade.
- Segurança das rotas utilizando JWT
- Documentação no Swagger

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
       git clone https://github.com/VitorKreis/Med.Voll.git
     ```
2. Navegue até o diretório do projeto:
      ```
       cd Med.voll
     ```
3. Instale as dependências:
     ```
       mvn install
     ```

## Uso
Para iniciar a aplicação, execute:
   ```
     mvn spring-boot:run
   ```

## Autor
GitHub: VitorKreis

LinkedIn: VitorKreis

