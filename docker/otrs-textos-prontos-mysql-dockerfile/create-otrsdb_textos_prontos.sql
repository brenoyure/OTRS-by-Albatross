CREATE TABLE IF NOT EXISTS problema (
		id   SMALLINT PRIMARY KEY AUTO_INCREMENT,
		tipo VARCHAR (20) NOT NULL UNIQUE);

CREATE TABLE IF NOT EXISTS descricao_problema (
        id INT PRIMARY KEY AUTO_INCREMENT,
        descricao_resumida  VARCHAR (55)  UNIQUE NOT NULL,
        descricao_detalhada VARCHAR (255) UNIQUE NOT NULL,
        fk_problema_id      SMALLINT             NOT NULL
);

ALTER TABLE descricao_problema ADD FOREIGN KEY (fk_problema_id) REFERENCES problema(id);

DESC problema;
DESC descricao_problema;

