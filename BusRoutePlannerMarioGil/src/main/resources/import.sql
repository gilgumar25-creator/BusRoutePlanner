
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (101, 'Sevilla (Capital)', 'Utrera', 32.5, 1.20, 39.00, 3);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (102, 'Dos Hermanas', 'Alcalá de Guadaíra', 15.8, 1.10, 17.38, 2);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (103, 'Sevilla (Capital)', 'La Rinconada', 12.0, 1.15, 13.80, 4);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (104, 'Mairena del Aljarafe', 'Sevilla (Capital)', 9.5, 1.30, 12.35, 5);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (105, 'Utrera', 'Dos Hermanas', 24.2, 1.10, 26.62, 2);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (106, 'Alcalá de Guadaíra', 'Sevilla (Capital)', 18.0, 1.25, 22.50, 4);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (107, 'Palomares del Rio', 'Mairena del Aljarafe', 6.4, 1.40, 8.96, 2);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (108, 'Sevilla (Capital)', 'Écija', 86.1, 0.95, 81.80, 2);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (109, 'La Rinconada', 'Alcalá de Guadaíra', 28.7, 1.15, 33.01, 2);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (110, 'Dos Hermanas', 'Sevilla (Capital)', 14.3, 1.20, 17.16, 6);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (111, 'Écija', 'Utrera', 92.4, 0.90, 83.16, 1);
INSERT INTO route (codigo, origen, destino, distancia, preciokilometro, precio_viaje, numero_maximo_buses_simultaneos) 
VALUES (112, 'Sevilla (Capital)', 'Palomares del Rio', 11.2, 1.35, 15.12, 3);


INSERT INTO bus (matricula, capacidad, id_route) VALUES (1111, 55, 101);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (2222, 60, 101);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (3333, 45, 102);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (4444, 70, 103);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (5555, 55, 104);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (6666, 50, 105);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (7777, 65, 106);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (8888, 40, 107);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (9999, 55, 108);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (1234, 60, 110);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (5678, 75, 110);
INSERT INTO bus (matricula, capacidad, id_route) VALUES (9012, 55, 112);


INSERT INTO driver (licencia, nombre, estado) VALUES (9001, 'Mario Gil', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9002, 'Ana Silva', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9003, 'Carlos Pérez', 0); 
INSERT INTO driver (licencia, nombre, estado) VALUES (9004, 'Luisa Ramos', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9005, 'David Cobos', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9006, 'Elena Pozo', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9007, 'Alejandro Sanz', 0); 
INSERT INTO driver (licencia, nombre, estado) VALUES (9008, 'Beatriz Luna', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9009, 'Javier Naranjo', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9010, 'Rocío Jurado', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9011, 'Manuel Carrasco', 1);
INSERT INTO driver (licencia, nombre, estado) VALUES (9012, 'Patricia Conde', 0);


-- 2026-06-01 es Lunes -> MONDAY
INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-01', 'MONDAY', 'MANANA', 1111, 9001, 101);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-01', 'MONDAY', 'TARDE', 2222, 9002, 101);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-01', 'MONDAY', 'MANANA', 3333, 9003, 102);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-01', 'MONDAY', 'TARDE', 4444, 9004, 103);

-- 2026-06-02 es Martes -> TUESDAY
INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-02', 'TUESDAY', 'MANANA', 5555, 9005, 104);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-02', 'TUESDAY', 'TARDE', 6666, 9006, 105);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-02', 'TUESDAY', 'MANANA', 7777, 9007, 106);

-- 2026-06-03 es Miércoles -> WEDNESDAY
INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-03', 'WEDNESDAY', 'MANANA', 8888, 9008, 107);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-03', 'WEDNESDAY', 'TARDE', 9999, 9009, 108);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-03', 'WEDNESDAY', 'MANANA', 1234, 9010, 110);

INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-03', 'WEDNESDAY', 'TARDE', 5678, 9011, 110);

-- 2026-06-04 es Jueves -> THURSDAY
INSERT INTO planificacion_ruta (dia_semana, dia_semana2, horario, bus_matricula, driver_licencia, route_codigo) 
VALUES ('2026-06-04', 'THURSDAY', 'MANANA', 9012, 9012, 112);

