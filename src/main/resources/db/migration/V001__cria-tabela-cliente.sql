create table cliente (
	id 				BIGINT 			NOT NULL AUTO_INCREMENT,
	nome 			VARCHAR(20) 	NOT NULL,
	sobrenome 		VARCHAR(50) 	NOT NULL,
	email 			VARCHAR(255) 	NOT NULL,
	cpf 			VARCHAR(14) 	NOT NULL,
	dt_nascimento 	DATETIME	 	NOT NULL,
	foto 			VARCHAR(60),
	PRIMARY KEY(id)
);
