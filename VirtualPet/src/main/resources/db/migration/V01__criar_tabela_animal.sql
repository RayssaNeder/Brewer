CREATE TABLE especie (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE raca (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    codigo_especie BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_especie) REFERENCES especie(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE animal (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    apelido VARCHAR(10) NOT NULL,
	pai VARCHAR(10) NOT NULL,
	mae VARCHAR(10) NOT NULL,
	statu VARCHAR(10) NOT NULL,
    sexo VARCHAR(80) NOT NULL,
    especie VARCHAR(50) NOT NULL,
    raca VARCHAR(50) NOT NULL,
    pelagem VARCHAR(50) NOT NULL,
    porte VARCHAR(50) NOT NULL,
    peso VARCHAR(50) NOT NULL,
    observacao VARCHAR(70) NOT NULL,
	codigo_especie BIGINT(20) NOT NULL,
    FOREIGN KEY (codigo_especie) REFERENCES especie(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO especie VALUES (0, 'Canino');
INSERT INTO especie VALUES (0, 'Felino');
INSERT INTO especie VALUES (0, 'Roedor');
INSERT INTO especie VALUES (0, 'Aves');


INSERT INTO raca VALUES (0, 'Yorkshire Terrier', 1);


