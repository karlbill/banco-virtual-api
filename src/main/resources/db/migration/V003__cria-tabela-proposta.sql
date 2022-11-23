CREATE TABLE proposta (
	id 				BIGINT 			NOT NULL AUTO_INCREMENT,
	cliente_id 		BIGINT		 	NOT NULL,
	estado 			VARCHAR(255) 	NOT NULL,
	dt_abertura 	VARCHAR(30) 	NOT NULL,
	
    PRIMARY KEY(id)
);

ALTER TABLE proposta ADD CONSTRAINT fk_proposta_cliente
FOREIGN KEY (cliente_id) REFERENCES cliente(id);