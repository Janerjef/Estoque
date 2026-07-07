USE estoque;

    CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    
    username VARCHAR(50) NOT NULL,
    psw VARCHAR(255) NOT NULL,
    
    nameFirst VARCHAR(100),
    sobrenome VARCHAR(100),
    
    matricula VARCHAR(50),
    cpf VARCHAR(14),
    
    sexo VARCHAR(10),
    dtaNascimento DATE,
    
    email VARCHAR(150),
    telefone VARCHAR(20),
    
    funcao VARCHAR(100),
    
    cep VARCHAR(10),
    endereco VARCHAR(150),
    numero VARCHAR(10),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    complemento VARCHAR(150),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO users (
    username, psw, nameFirst, sobrenome, matricula, cpf,
    sexo, dtaNascimento, email, telefone, funcao,
    cep, endereco, numero, bairro, cidade, estado, complemento
) VALUES (
    'admin', '$2a$10$htQv/2ebpKfJiG0eYwbB/erAwhcsjkR/oStjPhwR.QQOyWqC3uUmK', 'Admin', 'Sistema', '0001', '000.000.000-00',
    'Masculino', '1990-01-01', 'admin@email.com', '71999999999', 'Administrador',
    '40000-000', 'Rua Exemplo', '123', 'Centro', 'Salvador', 'BA', 'N/A'
);

CREATE TABLE produtos
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    codigo_barras   varchar(100) not null,
    nome_produtos   varchar(255) not null,
    fabricante      varchar(255),
    data_fabricacao date,
    data_vencimento date,
    quantidade      bigint,
    valor           decimal(10, 2),
    total           decimal(10, 2),
    status          varchar(100)
);

ALTER TABLE produtos
    ADD COLUMN  marca VARCHAR(100);



ALTER TABLE produtos
    ADD COLUMN  prateleira VARCHAR(100);


ALTER TABLE produtos
    ADD COLUMN  estoque_minimo INT DEFAULT 0;

CREATE TABLE prateleiras (
                             id INT AUTO_INCREMENT PRIMARY KEY,
                             nome VARCHAR(100) NOT NULL,
                             descricao VARCHAR(255)
);

INSERT INTO prateleiras (nome, descricao)
values ('Espaço 1','Local padrão de armazenamento de Produtos 1'),
       ('Espaço 2', 'Local padrão de armazenamento de Produtos 2 ');

ALTER TABLE produtos
    DROP COLUMN prateleira;

ALTER TABLE produtos
    ADD COLUMN prateleira_id INT;

ALTER TABLE produtos ADD CONSTRAINT fk_prateleira
    FOREIGN KEY (prateleira_id) REFERENCES prateleiras(id);

ALTER TABLE prateleiras
    RENAME COLUMN id TO prateleira_id;