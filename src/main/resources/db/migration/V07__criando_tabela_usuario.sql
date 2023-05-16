CREATE TABLE usuario(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
usuario VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL,
perfil_id BIGINT,
CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) REFERENCES perfil(id));