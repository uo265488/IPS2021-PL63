--Primero se deben borrar todas las tablas(de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:

drop table Sugerencias;
create table Sugerencias (idArticulo int foreing key, idRevisor int foreing key);

drop table Usuarios;
create table Usuarios(idUsuario int primary key, nombre varchar(32), tipoUsuario varchar(32));

drop table Revisores;
create table Revisores (idRevisor int primary key, nombre varchar(32), estado varchar(32), correo varchar(32), especialidad varchar(32));

drop table Articulos;
create table Articulos (idArticulo int primary key, titulo varchar(32), primerAutor varchar(32), estado varchar(32), resumen varchar(100), palabrasClave varchar(32), ficheroFuente varchar(32), cartaPresentacion varchar(32), CV varchar(32), firma bool, vecesRevisado int, versionDefinitiva bool, DOI varchar(32),fecha varchar(32), volumen int, debatible bool);

drop table Autores;
create table Autores (idAutor int primary key, nombre varchar(32), dni varchar(32));

drop table Debates;
create table Debates (idDebate int varchar(32), idArticulo varchar(32), fecha varchar(32), abierto bool);

drop table Mensajes;
create table Mensajes (idMensaje varchar(32), idDebate int varchar(32), mensaje varchar(140));

drop table AutoresSecundarios;
create table AutoresSecundarios (idArticulo int foreing key, idAutor int foreing key);

drop table Revisiones;
create table Revisiones (idArticulo int foreing key, idRevisor int foreing, numeroRevision int, fecha varchar(32), comentariosAutor varchar(100), comentariosEditor varchar(100), decision varchar(50), enviarAlEditor bool, estadoDeLaPropuesta varchar(32),
	constraint pk_revisiones primary key (idArticulo, idRevisor, numeroRevision));
