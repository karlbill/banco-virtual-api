CREATE TABLE endereco (
    id 			BIGINT 			NOT NULL AUTO_INCREMENT,
    cliente_id	BIGINT			NOT NULL,
    cep			VARCHAR(9) 		NOT NULL,
    rua 		VARCHAR(60) 	NOT NULL,
    bairro 		VARCHAR(60) 	NOT NULL,
    complemento VARCHAR(60)		NOT NULL,
    cidade	 	VARCHAR(60)		NOT NULL,
    estado		VARCHAR(60)		NOT NULL,
    
    PRIMARY KEY (id)
 );
 
 ALTER TABLE endereco ADD CONSTRAINT fk_endereco_cliente
 FOREIGN KEY (cliente_id) REFERENCES cliente(id);
 