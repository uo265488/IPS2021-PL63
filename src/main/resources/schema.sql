--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table Carreras;
create table Carreras (id int primary key not null, inicio date not null, fin date not null, fecha date not null, descr varchar(32), check(inicio<=fin), check(fin<fecha));

drop table Revisores;
create table Revisores (id int primary key, nombre varchar(32), estado varchar(32));

drop table Usuarios;
create table Usuarios (nombre varchar(32) primary key, tipoUsuario varchar(32));

drop table Articulos;
create table Articulos (id varchar(32) primary key, titulo varchar(32), primerAutor varchar(32), otrosAutores varchar(32), estado varchar(32),
						resumen varchar(255), palabrasClave varchar(255), ficheroFuente varchar(32), cartaPresentacion varchar(32), cvAutor varchar(32), firma varchar(32));

