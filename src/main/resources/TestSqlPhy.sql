DROP DATABASE IF EXISTS test;
CREATE DATABASE test;
USE test;

DROP TABLE IF EXISTS blog;
-- Estos nombres tienen que coincidir en JPA de java
CREATE TABLE IF NOT EXISTS blog (
 id_b INT AUTO_INCREMENT PRIMARY KEY,
 title_b VARCHAR(60),
 content_b TEXT not NULL,
 category_b VARCHAR(30),
 tags_b JSON,
 create_date DATE,
 update_date DATE
);

-- Nombrar titulo
DELIMITER $$
DROP TRIGGER IF EXISTS title_create$$
CREATE TRIGGER title_create BEFORE INSERT
ON blog
FOR EACH ROW
BEGIN
    IF NEW.title_b IS NULL THEN
        SET NEW.title_b = CONCAT('Creado: ',LEFT(NEW.content_b, 4));
    END IF;
END$$
DELIMITER ;

-- Crear fecha actual
DELIMITER $$
DROP TRIGGER IF EXISTS date_create$$
CREATE TRIGGER date_create BEFORE INSERT 
ON blog
FOR EACH ROW
BEGIN 
	IF NEW.create_date is NULL THEN
		SET NEW.create_date = curdate();
	END IF;
END $$
DELIMITER ;

-- Registrar actualización
DELIMITER $$
DROP TRIGGER IF EXISTS update_date$$
CREATE TRIGGER update_date BEFORE UPDATE
ON blog
FOR EACH ROW
BEGIN
-- Sumarle un día o restarle un día le resta sentido a la operación,
-- pero es la manera más viable de ver al momento su funcionamiento.
	SET NEW.update_date =  DATE_ADD(CURDATE(), INTERVAL 1 DAY );
END$$
DELIMITER ;




    
    
    
    
    
    
    
    
    
    
    
    
/*
DELIMITER $$
DROP TRIGGER IF EXISTS revision_publicaciones_fecha$$
CREATE TRIGGER revision_publicaciones_fecha BEFORE INSERT
ON Publicaciones 
FOR EACH ROW
BEGIN
  IF NEW.fecha_Creacion IS NULL THEN
        SET NEW.fecha_Creacion = CURDATE();
    END IF;
END $$
DELIMITER ;  
*/