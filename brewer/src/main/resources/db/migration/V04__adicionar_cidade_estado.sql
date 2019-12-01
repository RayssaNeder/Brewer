CREATE TABLE estado (
    codigo BIGINT(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    sigla VARCHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE cidade (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    codigo_estado BIGINT(20) NOT NULL,
     FOREIGN KEY (codigo_estado) REFERENCES estado(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO estado (codigo, nome, sigla) VALUES (0, 'Paraíba', 'PB');
INSERT INTO estado (codigo, nome, sigla) VALUES (1, 'Pernambuco', 'PB');
INSERT INTO estado (codigo, nome, sigla) VALUES (2, 'Bahia', 'BA');

INSERT INTO cidade (nome, codigo_estado ) VALUES ('João Pessoa',0);
INSERT INTO cidade (nome, codigo_estado ) VALUES ('Recife',1);
INSERT INTO cidade (nome, codigo_estado ) VALUES ('Cabedelo',0);