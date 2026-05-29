INSERT INTO usuario (id, nombre, password, rol) VALUES (1, 'admin', '{noop}admin', 'ADMIN');
INSERT INTO admin (id, fecha_nombramiento) VALUES (1, '2024-01-01');

-- Usuario base OPERADOR
INSERT INTO usuario (id, nombre, password, rol) VALUES (2, 'operador', '{noop}operador', 'OPERADOR');
INSERT INTO operador (id, activo) VALUES (2, true);

ALTER TABLE usuario ALTER COLUMN id RESTART WITH 3;