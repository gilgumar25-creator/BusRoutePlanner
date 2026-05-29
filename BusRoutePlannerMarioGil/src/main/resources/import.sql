/*-- Insertar ADMIN solo si no existe el ID 1
INSERT INTO usuario (id, nombre, password, rol) 
SELECT 1, 'admin', '{noop}admin', 'ADMIN' 
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE id = 1);

INSERT INTO admin (id, fecha_nombramiento) 
SELECT 1, '2024-01-01' 
WHERE NOT EXISTS (SELECT 1 FROM admin WHERE id = 1);


-- Insertar OPERADOR solo si no existe el ID 2
INSERT INTO usuario (id, nombre, password, rol) 
SELECT 2, 'operador', '{noop}operador', 'OPERADOR' 
WHERE NOT EXISTS (SELECT 1 FROM usuario WHERE id = 2);

INSERT INTO operador (id, activo) 
SELECT 2, true 
WHERE NOT EXISTS (SELECT 1 FROM operador WHERE id = 2);


-- Asegurar que los nuevos usuarios que crees desde la app arranquen a partir del ID 3
ALTER TABLE usuario ALTER COLUMN id RESTART WITH 3;*/