SET SQL_SAFE_UPDATES = 0;
DELETE FROM cola;
DELETE FROM pagos;
DELETE FROM reservas;
DELETE FROM inscripciones;
DELETE FROM clientes;
DELETE FROM sesiones;
DELETE FROM actividades;
DELETE FROM instalaciones;
DELETE FROM periodos_inscripcion;
INSERT INTO clientes(nombre, dni, id_socio, fecha_nacimiento, PagoPendiente, contraseña, cuotaInicial, cuotaReservas, cuotaActividades, telefono, direccion) VALUES
('Juan Pérez', '11111111A', 1, '1980-05-20', 1, 'contraseña1', 50.00, 10.00, 15.00, '666555444', 'Calle Mayor 1'),
('María García', '22222222B', null, null, 0, null, null, null, null, '666444333', null),
('Pedro Fernández', '33333333C', 3, '1990-07-03', 0, 'contraseña3', 50.00, 15.00, 25.00, '666333222', 'Plaza España 8'),
('Sara Gómez', '44444444D', 4, '1995-10-15', 1, 'contraseña4', 0, 17.50, 0, '666222111', 'Avenida Libertad 12'),
('Luisa Martínez', '55555555E', 5, '2000-01-01', 0, 'contraseña5', 0, 20.00, 35.00, '666111000', 'Calle Nueva 17'),
('Mario Rodríguez', '66666666F', null, null, 0, null, null, null, null, '666000999', null),
('Elena Sánchez', '77777777G', 7, '1978-09-25', 1, 'contraseña7', 0, 25.00, 45.00, '666999888', 'Calle Toledo 33'),
('Javier Alonso', '88888888H', null, null, 0, null, null, null, null, '666888777', null),
('Lucía García', '99999999I', 9, '1992-11-18', 0, 'contraseña9', 0, 30.00, 0, '666777666', 'Calle San Juan 51'),
('Carlos Pérez', '11111111J', null, null, 0, null, null, null, null, '666666555', null);


INSERT INTO instalaciones(id_instalacion, nombre, deporte, precio) VALUES 
(1, 'Piscina Olímpica', 'Natación', 10.00),
(2, 'Cancha de Tenis', 'Tenis', 15.00),
(3, 'Gimnasio Principal', 'Fitness', 20.00),
(4, 'Pista de Pádel', 'Pádel', 12.50),
(5, 'Circuito de Running', 'Running', 5.00),
(6, 'Campo de Fútbol', 'Fútbol', 5.00);

 
INSERT INTO reservas(id_reserva, id_socios, id_instalaciones, fecha, fecha_reserva, precio, actividad) VALUES 
(0,NULL,NULL,NULL,NULL,NULL,NULL),
(1, '1', 1,  '2023-02-01', '2023-04-6 15:00:00', 10.00,4),
(2, '1', 3,  '2023-02-02', '2023-04-11 16:00:00', 15.00,0),
(3, '4', 6,  '2023-02-03', '2023-04-11 17:00:00', 20.00,1),
(4, '5', 3,  '2023-02-04', '2023-04-12 18:00:00', 12.50,3),
(5, '7', 1,  '2023-02-05', '2023-04-13 19:00:00', 5.00,0),
(6, '9', 3,  '2023-02-07', '2023-04-14 21:00:00', 15.00,0);

 
INSERT INTO periodos_inscripcion(id_periodo_inscripcion, nombre_periodo, descripcion, fecha_inicio_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES 
(1, 'Periodo 1', 'Periodo Verano', '2023-2-24', '2023-3-5', '2023-7-30'), 
(2, 'Periodo 2', 'Periodo Otoño','2023-2-22', '2023-4-3', '2023-10-30'), 
(3, 'Periodo 3', 'Periodo Invierno','2023-1-20', '2023-5-4', '2023-7-27'); 


INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, plazas_disponibles, precio_socio, precio_no_socio, fecha_inicio, fecha_fin, deporte , id_instalaciones, id_periodo_inscripciones) VALUES 
(1, 'Clase de yoga 1', 'Clase de yoga para todos los niveles', 20, 20, 5.00, 10.00, '2023-04-13', '2023-05-01', 'Yoga', 3, 3),
(2, 'Partido de fútbol 2', 'Solteros vs Casados', 22, 22, 0.00, 5.00, '2023-04-13', '2023-05-02', 'Fútbol', 6, 1),
(3, 'Clase de natación 2', 'Clase de natación para gente que no sabe nadar', 15, 15, 10.00, 15.00, '2023-03-03', '2023-04-03', 'Natación', 1, 3),
(4, 'Entrenamiento de boxeo 1', 'Entrenamiento de boxeo para todos los niveles', 12, 12, 7.00, 12.00, '2023-03-04', '2023-04-04', 'Boxeo', 3, 3),
(5, 'Clase de baile 5', 'Clase de baile para principiantes', 25, 25, 8.00, 13.00, '2023-03-05', '2023-04-05', 'Baile', 3, 1),
(6, 'Partido pádel 65', 'Pepe se enfrenta a tí', 4, 0, 8.00, 13.00, '2023-03-05', '2023-04-05', 'Pádel', 4, 3);


INSERT INTO inscripciones(id_inscripcion, dni_clientes, id_actividades, fecha) VALUES
(1,'11111111A', 1, '2023-2-23 17:00:00'), 
(2,'11111111A', 3,'2023-2-24 18:00:00'), 
(3,'55555555E', 1,'2023-2-25 19:00:00'),
(4,'77777777G', 4, '2023-2-26 20:00:00'),
(5,'88888888H', 2, '2023-3-27 21:00:00');



INSERT INTO sesiones(id_sesion, dia, hora_inicio, hora_fin, id_actividades) VALUES 
(1, 'lunes', '13:00:00', '15:00:00', '3'), 
(2, 'martes', '12:00:00', '13:00:00', '2'), 
(3, 'miércoles', '19:00:00', '21:00:00', '6'),
(4, 'jueves', '20:00:00', '22:00:00', '4'), 
(5, 'viernes', '10:00:00', '12:00:00', '1'); 


INSERT INTO pagos(id_pago, fecha, dni_clientes, id_inscripciones, id_reservas) VALUES
(1, '2022-03-14', '44444444D', NULL, 6),
(2, '2022-03-14', '44444444D', NULL, 3),
(3, '2022-03-10', '55555555E', 3, 3),
(4, '2022-02-26', '11111111A', 2, 4),
(5, '2022-02-25', '44444444D', NULL, 6),
(6, '2022-02-25', '88888888H', 5, 3),
(7, '2022-03-25', '77777777G', 4, 1);

INSERT INTO cola(id_cola, dni_clientes, id_actividades, fecha,socio) VALUES
(0,NULL,NULL,NULL,NULL),
(1,'11111111A', 1, '2023-04-15 10:00:00', 'sí'),
(2,'11111111A', 2, '2023-04-15 10:00:00', 'sí'),
(3,'88888888H', 2, '2023-04-18 11:00:00', 'no'),
(4,'55555555E', 2, '2023-04-17 16:00:00', 'sí'),
(5,'44444444D', 3, '2023-04-20 10:00:00', 'sí'),
(6,'11111111J', 1, '2023-04-20 10:00:00', 'no');