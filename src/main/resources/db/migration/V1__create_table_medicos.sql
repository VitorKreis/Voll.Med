CREATE TABLE medicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    telefone VARCHAR(100) NOT NULL,
    crm VARCHAR(100) NOT NULL UNIQUE,
    especialidade VARCHAR(100) NOT NULL,
    tipo VARCHAR(100) NOT NULL,
    rua VARCHAR(255) NOT NULL,
    bairro VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    complemento VARCHAR(100),
    numero VARCHAR(20),
    uf CHAR(2) NOT NULL
);