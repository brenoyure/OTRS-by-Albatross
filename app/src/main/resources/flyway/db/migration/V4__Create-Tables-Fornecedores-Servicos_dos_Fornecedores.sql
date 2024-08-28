
CREATE TABLE IF NOT EXISTS fornecedores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255) UNIQUE NOT NULL,
    emails TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS servicos_do_fornecedor (
    fk_fornecedor_id INT,
    id_do_servico_no_sistema_de_chamados INT NOT NULL
);

ALTER TABLE servicos_do_fornecedor ADD FOREIGN KEY (fk_fornecedor_id) REFERENCES fornecedores(id);

DESC fornecedores;
DESC servicos_do_fornecedor;
