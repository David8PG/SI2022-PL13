DELETE FROM clientes;
DELETE FROM instalaciones;
DELETE FROM reservas;
DELETE FROM periodos_inscripcion;
DELETE FROM actividades;
DELETE FROM inscripciones;
DELETE FROM sesiones;
DELETE FROM pagos;

INSERT INTO clientes(nombre, dni, id_socio, fecha_nacimiento, PagoPendiente, contraseña, cuotaInicial, cuotaReservas, cuotaActividades, telefono, direccion) VALUES
('Juan Pérez', '11111111A', 1, '1980-05-20', 0, 'contraseña1', 50.00, 10.00, 15.00, '666555444', 'Calle Mayor 1'),
('María García', '22222222B', null, null, 0, null, null, null, null, '666444333', null),
('Pedro Fernández', '33333333C', 3, '1990-07-03', 0, 'contraseña3', 50.00, 15.00, 25.00, '666333222', 'Plaza España 8'),
('Sara Gómez', '44444444D', 4, '1995-10-15', 0, 'contraseña4', 0, 17.50, 0, '666222111', 'Avenida Libertad 12'),
('Luisa Martínez', '55555555E', 5, '2000-01-01', 0, 'contraseña5', 0, 20.00, 35.00, '666111000', 'Calle Nueva 17'),
('Mario Rodríguez', '66666666F', null, null, 0, null, null, null, null, '666000999', null),
('Elena Sánchez', '77777777G', 7, '1978-09-25', 1, 'contraseña7', 0, 25.00, 45.00, '666999888', 'Calle Toledo 33'),
('Javier Alonso', '88888888H', 8, '1975-04-05', 1, 'contraseña8', 0, 27.50, 50.00, '666888777', 'Calle Gran Vía 45'),
('Lucía García', '99999999I', 9, '1992-11-18', 0, 'contraseña9', 0, 30.00, 0, '666777666', 'Calle San Juan 51'),
('Carlos Pérez', '11111111J', null, null, 0, null, null, null, null, '666666555', null);


INSERT INTO instalaciones(id_instalacion, nombre, deporte, precio) VALUES 
(1, 'Piscina Olímpica', 'Natación', 10.00),
(2, 'Cancha de Tenis', 'Tenis', 15.00),
(3, 'Gimnasio Principal', 'Fitness', 20.00),
(4, 'Pista de Pádel', 'Pádel', 12.50),
(5, 'Circuito de Running', 'Running', 5.00);
(6, 'Campo de Fútbol', 'Fútbol', 5.00);

 
INSERT INTO reservas(id_reserva, id_socios, id_instalaciones, fecha_inscripcion, fecha_reserva, precio) VALUES 
//CAMBIAR FECHAS
(1, '1', 1,  '2023-02-01', '2023-03-01 15:00:00', 10.00),
(2, '3', 3,  '2023-02-02', '2023-03-02 16:00:00', 15.00),
(3, '4', 6,  '2023-02-03', '2023-03-03 17:00:00', 20.00),
(4, '5', 3,  '2023-02-04', '2023-03-04 18:00:00', 12.50),
(5, '7', 1,  '2023-02-05', '2023-03-05 19:00:00', 5.00),
(6, '8', 1,  '2023-02-06', '2023-03-06 20:00:00', 10.00),
(7, '9', 3,  '2023-02-07', '2023-03-07 21:00:00', 15.00);

 
INSERT INTO periodos_inscripcion(id_periodo_inscripcion, nombre_periodo, descripcion, fecha_inicio_socio, fecha_fin_socio, fecha_fin_no_socio) VALUES 
(1, 'Periodo 1', 'Periodo Verano', '2023-6-24', '2023-7-5', '2023-7-30'), 
(2, 'Periodo 2', 'Periodo Otoño','2023-9-22', '2023-10-3', '2023-10-30'), 
(3, 'Periodo 3', 'Periodo Invierno','2023-1-20', '2023-2-4', '2023-2-30'); 


 INSERT INTO actividades(id_actividad, nombre, descripcion, aforo, plazas_disponibles, precio_socio, precio_no_socio, fecha_inicio, fecha_fin, deporte , id_instalaciones, id_periodo_inscripciones) VALUES 
(1, 'Clase de yoga', 'Clase de yoga para todos los niveles', 20, 20, 5.00, 10.00, '2023-03-01', '2023-04-01', 'Yoga', 3, 1),
(2, 'Partido de fútbol', 'Solteros vs Casados', 22, 22, 0.00, 5.00, '2023-03-02', '2023-03-02', 'Fútbol', 6, 1),
(3, 'Clase de natación', 'Clase de natación para gente que no sabe nadar', 15, 15, 10.00, 15.00, '2023-03-03', '2023-04-03', 'Natación', 1, 1),
(4, 'Entrenamiento de boxeo', 'Entrenamiento de boxeo para todos los niveles', 12, 12, 7.00, 12.00, '2023-03-04', '2023-04-04', 'Boxeo', 3, 1),
(5, 'Clase de baile', 'Clase de baile para principiantes', 25, 25, 8.00, 13.00, '2023-03-05', '2023-04-05', 'Baile', 3, 1);
(6, 'Partido pádel', 'Pepe se enfrenta a tí', 4, 4, 8.00, 13.00, '2023-03-05', '2023-04-05', 'Pádel', 4, 1);


INSERT INTO inscripciones(id_inscripcion, dni_clientes, id_actividades, fecha_inscripción) VALUES 
(1,'44444444D', 1, '2023-2-23 17:00:00'), 
(2,'44444444D', 2,'2023-2-24 18:00:00'), 
(3,'44444444D', 3,'2023-2-25 19:00:00'),
(4,'44444444D', 1, '2023-2-26 20:00:00'),
(5,'44444444D', 2, '2023-3-27 21:00:00');



INSERT INTO sesiones(id_sesion, dia, hora_inicio, hora_fin, id_actividades) VALUES 
(1, 'lunes', '13:00:00', '15:00:00', '3'), 
(2, 'martes', '12:00:00', '13:00:00', '2'), 
(3, 'miércoles', '19:00:00', '21:00:00', '6'),
(4, 'jueves', '20:00:00', '22:00:00', '4'); 
(5, 'viernes', '10:00:00', '12:00:00', '1'); 


INSERT INTO pagos(id_pago, fecha, dni_clientes, id_inscripciones, id_reservas) VALUES
(1, '2022-03-14', '44444444D', NULL, 6),
(2, '2022-03-14', '44444444D', NULL, 3),
(3, '2022-03-10', '66666666F', 3, 0),
(4, '2022-02-26', '66666666F', 2, 0),
(5, '2022-02-25', '44444444D', NULL, 7),
(6, '2022-03-29', '44444444D', NULL, 6),
(7, '2022-03-25', '66666666F', 4, 0);

