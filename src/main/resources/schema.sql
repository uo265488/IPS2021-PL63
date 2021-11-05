--Primero se deben borrar todas las tablas(de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:

drop table Usuarios;
create table Usuarios(idUsuario int primary key, nombre varchar(32), tipoUsuario varchar(32));

drop table Revisores;
create table Revisores (idRevisor int primary key, nombre varchar(32), estado varchar(32));

drop table Articulos;
create table Articulos (idArticulo int primary key, titulo varchar(32), primerAutor varchar(32), estado varchar(32), resumen varchar(100), palabrasClave varchar(32), ficheroFuente varchar(32), cartaPresentacion varchar(32), CV varchar(32), firma bool, vecesRevisado int, versionDefinitiva bool, DOI varchar(32),fecha varchar(32), volumen int);

drop table Autores;
create table Autores (idAutor int primary key, nombre varchar(32), dni varchar(32));

drop table ArticulosDeAutores;
create table articulosDeAutores (idArticulo int foreing key, idAutor int foreing key);

drop table Revisiones;
create table Revisiones (idArticulo int foreing key, idRevisor int foreing key, fecha varchar(32), comentariosAutor varchar(100), comentariosEditor varchar(100), decision varchar(50), enviarAlEditor varchar(32), estadoDeLaPropuesta varchar(32));
