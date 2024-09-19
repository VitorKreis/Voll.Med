# Voll.Med

**Voll.Med** é uma API RESTful desenvolvida em Java utilizando o framework Spring Boot. O projeto simula um sistema de gerenciamento de clínica médica, permitindo a realização de operações de CRUD (Create, Read, Update, Delete) para médicos e pacientes. A aplicação também oferece funcionalidades para agendamento de consultas e controle de acesso aos endpoints, além de incluir testes automatizados para garantir a qualidade do código.

## Funcionalidades
- **Agendamento de Consultas**: Permite agendar consultas médicas com base em especialidades ou diretamente com um médico específico.
- **Gestão de Médicos e Pacientes**: Operações de CRUD para gerenciar médicos e pacientes na clínica.
- **Controle de Acesso (Roles)**: Controle de permissões com diferentes níveis de acesso para médicos, gerentes e enfermeiras, garantindo a segurança da aplicação.
- **Documentação Automatizada com Swagger**: A API possui uma interface amigável para visualizar e testar os endpoints via Swagger.
- **Testes Automatizados**: Testes unitários e de integração garantem a qualidade e funcionamento da aplicação.

## Tecnologias Utilizadas
- **Java 11+**: Linguagem de programação principal.
- **Spring Boot**: Framework para a criação de aplicações web robustas e escaláveis.
- **Spring Security**: Gerenciamento de autenticação e controle de permissões.
- **Spring Data JPA**: Persistência de dados com JPA e integração com bancos de dados relacionais.
- **PostgreSQL**: Banco de dados relacional utilizado no projeto.
- **JUnit**: Framework para testes unitários.
- **Docker**: Para facilitar a execução de ambientes e gerenciamento de containers.
- **Swagger**: Documentação da API e interface para testes de endpoints.

## Pré-requisitos
- **Java JDK 11** ou superior
- **Maven 3.6** ou superior
- **Docker** para rodar o banco de dados PostgreSQL

## Instalação e Execução

Siga os passos abaixo para rodar a aplicação localmente:

1. Clone o repositório:
   ```bash
   git clone https://github.com/VitorKreis/Med.Voll.Api_Rest.git
   ```
   
2. Navegue até o diretório do projeto:
   ```bash
   cd Med.voll.Api_Rest
   ```

3. Instale as dependências:
   ```bash
   mvn install
   ```

4. Inicie os serviços com Docker (certifique-se de que Docker está instalado e rodando):
   ```bash
   docker-compose up
   ```

5. Rode os testes automatizados:
   ```bash
   mvn test
   ```

6. Acesse a documentação Swagger para explorar e testar os endpoints:
   ```
   http://localhost:8090/swagger-ui/index.html
   ```

## Endpoints Principais

A API expõe os seguintes endpoints. A base URL é `http://localhost:8090`:

### **Médicos**
* `GET /medico` - Lista todos os médicos (Acessível por qualquer usuário)
* `GET /medico/{id}` - Retorna um médico específico por ID (Acessível por qualquer usuário)
* `POST /medico` - Cadastra um novo médico (Acessível para usuários com as roles **MEDICO** e **GERENTE**)
* `PUT /medico/{id}` - Atualiza os dados de um médico (Acessível para usuários com as roles **MEDICO** e **GERENTE**)
* `DELETE /medico/{id}` - Exclui um médico (Acessível apenas para usuários com a role **GERENTE**)

### **Pacientes**
* `GET /paciente` - Lista todos os pacientes (Acessível por qualquer usuário)
* `GET /paciente/{id}` - Retorna um paciente específico por ID (Acessível por qualquer usuário)
* `POST /paciente` - Cadastra um novo paciente (Acessível para usuários com as roles **MEDICO**, **GERENTE**, e **ENFERMEIRA**)
* `PUT /paciente/{id}` - Atualiza os dados de um paciente (Acessível para usuários com as roles **MEDICO**, **GERENTE**, e **ENFERMEIRA**)
* `DELETE /paciente/{id}` - Exclui um paciente (Acessível para usuários com as roles **MEDICO**, **GERENTE**, e **ENFERMEIRA**)

### **Consultas**
* `GET /consultas` - Lista todas as consultas agendadas (Acessível por qualquer usuário)
* `GET /consultas/{id}` - Retorna os detalhes de uma consulta específica por ID (Acessível por qualquer usuário)
* `POST /consultas` - Agendamento de uma nova consulta (Acessível para usuários com as roles **MEDICO**, **GERENTE**, e **ENFERMEIRA**)
* `DELETE /consultas/{id}` - Cancela uma consulta (Acessível apenas para usuários com a role **GERENTE**)

## Testes
Os testes cobrem tanto a unidade quanto a integração da aplicação, garantindo que as funcionalidades principais estejam operando corretamente. Para executar todos os testes, utilize o comando:
```bash
mvn test
```

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir uma *issue* ou enviar um *pull request* com melhorias, correções ou novas funcionalidades.
