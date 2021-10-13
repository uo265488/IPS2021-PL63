--Primero se deben borrar todas las tablas (de detalle a maestro) y lugo anyadirlas (de maestro a detalle)
--(en este caso en cada una de las aplicaciones (tkrun y descuento) se usa solo una tabla, por lo que no hace falta)

--Para giis.demo.tkrun:
drop table Carreras;
create table Carreras (id int primary key not null, inicio date not null, fin date not null, fecha date not null, descr varchar(32), check(inicio<=fin), check(fin<fecha));

drop table Revisores;
create table Revisores (id int primary key, nombre varchar(32), estado varchar(32));

drop table Articulo;
create table Articulo (idArticulo int primary key, titulo varchar(32), primerAutor varchar(32), estado varchar(32), resumen varchar(100), palabrasClave varchar(32), ficheroFuente varchar(32), cartaPresentacion varchar(32), CV varchar(32), firma bool, revisado bool);

drop table Autores;
create table Autores (idAutor int primary key, nombre varchar(32), dni varchar(32));

drop table articulosDeAutores;
create table articulosDeAutores (idArticulo int foreing key, idAutor int foreing key);

drop table articulosRevisores;
create table articulosRevisores(idArticulo int foreing key, id int foreing key,comentariosAutor varchar(100),comentariosEditor varchar(100), decision varchar(32), enviarAlEditor bool);

drop table revisiones;
create table revisiones(id int foreing key, idArticulo int foreing key, comentariosAutor varchar(100), comentariosEditor varchar(100),decision varchar(32), enviadoAlEditor bool, fecha date not null);

