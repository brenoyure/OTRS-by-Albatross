CREATE DATABASE IF NOT EXISTS otrsdb_textos_prontos;

USE otrsdb_textos_prontos;

CREATE TABLE IF NOT EXISTS problema (
		id SMALLINT PRIMARY KEY AUTO_INCREMENT,
		tipo                          VARCHAR (20) NOT NULL UNIQUE);
                                
CREATE TABLE IF NOT EXISTS descricao_problema (
        id INT PRIMARY KEY AUTO_INCREMENT,
        descricao_resumida  VARCHAR (55) NOT NULL UNIQUE,
        descricao_detalhada VARCHAR (255) NOT NULL UNIQUE,
        fk_problema_id SMALLINT NOT NULL
);

ALTER TABLE descricao_problema ADD FOREIGN KEY (fk_problema_id) REFERENCES problema(id);

DESC problema;
DESC descricao_problema;

