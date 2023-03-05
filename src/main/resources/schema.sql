DROP TABLE IF EXISTS pagos;
DROP TABLE IF EXISTS reservas;
DROP TABLE IF EXISTS inscripciones;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS sesiones;
DROP TABLE IF EXISTS actividades;
DROP TABLE IF EXISTS instalaciones;
DROP TABLE IF EXISTS periodos_inscripcion;

CREATE TABLE clientes( 
    nombre varchar(40), 
    dni varchar(9) unique not null primary key,
    id_socio integer unsigned unique,  
    fecha_nacimiento date,
    PagoPendiente integer, 
    contraseña varchar(20),
    cuotaInicial double,
    cuotaReservas double,
    cuotaActividades double,
    telefono varchar(9),
    direccion varchar(40)
); 

CREATE TABLE instalaciones( 
    id_instalacion integer unsigned unique not null primary key, 
    nombre varchar(40) unique, 
    deporte varchar(40), 
    precio decimal(10,2) 
   );
   
CREATE TABLE reservas( 
    id_reserva integer unsigned unique not null primary key, 
    id_socios integer unsigned,
    id_instalaciones integer unsigned,
    foreign key (id_socios) references clientes(id_socio), 
    foreign key (id_instalaciones) references instalaciones(id_instalacion), 
    fecha datetime, 
    fecha_reserva datetime, 
    precio decimal(10,2),
    actividad integer unsigned
   ); 
   
CREATE TABLE periodos_inscripcion( 
    id_periodo_inscripcion integer unsigned unique not null primary key, 
	nombre_periodo varchar(40) unique, 
    descripcion varchar(200), 
    fecha_inicio_socio date, 
    fecha_fin_socio date, 
    fecha_fin_no_socio date 

); 

CREATE TABLE actividades( 
    id_actividad  integer unsigned unique not null primary key, 
    nombre varchar(50) unique, 
    descripcion varchar(200), 
    aforo integer, 
    plazas_disponibles integer,
    precio_socio decimal(10,2), 
    precio_no_socio decimal(10,2), 
    fecha_inicio date, 
    fecha_fin date, 
    deporte varchar(20), 
    id_instalaciones integer unsigned,
    id_periodo_inscripciones integer unsigned,
    foreign key (id_instalaciones) references instalaciones(id_instalacion),
    foreign key (id_periodo_inscripciones) references periodos_inscripcion(id_periodo_inscripcion) 

    );
    
CREATE TABLE inscripciones( 
    id_inscripcion integer unsigned unique not null primary key, 
    dni_clientes varchar(9),
    id_actividades integer unsigned,
    foreign key (dni_clientes) references clientes(dni), 
    foreign key (id_actividades) references actividades(id_actividad) ON DELETE CASCADE, 
    fecha datetime 
   ); 
   

CREATE TABLE sesiones( 
    id_sesion integer unsigned unique not null primary key, 
	dia varchar(40),  
    hora_inicio time, 
    hora_fin time,
    id_actividades integer unsigned,
    foreign key (id_actividades) references actividades(id_actividad)  ON DELETE CASCADE
); 

CREATE TABLE pagos( 
    id_pago integer unsigned unique not null primary key, 
	fecha date,
   	dni_clientes varchar(9),
   	foreign key (dni_clientes) references clientes(dni), 
    id_inscripciones integer unsigned unique,
    foreign key (id_inscripciones) references inscripciones(id_inscripcion) ON DELETE CASCADE, 
    id_reservas integer unsigned,
    foreign key (id_reservas) references reservas(id_reserva)

); 
