USE test;

-- Muestra básica
INSERT INTO blog (title_b, content_b, category_b, tags_b, create_date, update_date)
VALUES 
  ('Introducción a JavaScript', 'Contenido sobre JS básico...', 'Programación', '["JavaScript", "Frontend", "Web"]', CURDATE(), CURDATE()),
  ('Guía de CSS moderno', 'Flexbox, Grid y más...', 'Diseño Web', '["CSS", "Estilos", "Responsive"]', CURDATE(), CURDATE()),
  ('Bases de datos relacionales', 'Explicación de SQL y modelos...', 'Backend', '["SQL", "MySQL", "PostgreSQL"]', CURDATE(), CURDATE()),
  ('Machine Learning para principiantes', 'Conceptos clave de ML...', 'Inteligencia Artificial', '["ML", "Python", "Data Science"]', CURDATE(), CURDATE());
  
-- Lecturas básicas
SELECT * FROM blog;
SELECT * FROM blog
WHERE JSON_CONTAINS(tags_b, '["Python"]');
  


-- Insercción mínima y/o necesaria de valores base.
INSERT INTO blog (content_b, category_b)
VALUES ('LLLLLLLLLLL AAAAA LLLLLLLL AAAAAAAA', 'tECMOCPASDSADASDMNASMDAS');

INSERT INTO blog (title_b, content_b, category_b)
VALUES (NULL, 'Se tiene que quedar con las letras SET O SE', 'Prueba de manual');

SELECT * FROM blog;
-- Actualización básica	
 UPDATE blog
 set title_b = 'Cambio generico sin tilde hahah'
 where id_b = 1;
    
SELECT * FROM blog;
    