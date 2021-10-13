--Datos para carga inicial de la base de datos
delete from carreras;
insert into carreras(id,inicio,fin,fecha,descr) values 
	(100,'2016-10-05','2016-10-25','2016-11-09','finalizada'),
	(101,'2016-10-05','2016-10-25','2016-11-10','en fase 3'),
	(102,'2016-11-05','2016-11-09','2016-11-20','en fase 2'),
	(103,'2016-11-10','2016-11-15','2016-11-21','en fase 1'),
	(104,'2016-11-11','2016-11-15','2016-11-22','antes inscripcion');
	
delete from revisores;
insert into revisores(idRevisor,nombre,estado) values 
	(1,'felipe','disponible'),
	(2,'ernesto','disponible'),
	(3,'maria','no disponible'),
	(4,'Victorio Federico', 'disponible'),
	(5,'Carlo Antonio', 'disponible'),
	(6,'Feranando Octavo', 'disponible'),
	(7,'Fernando Infanzon', 'disponible');
	
delete from autores;
insert into autores(idAutor,nombre,dni) values 
	(4,'antonio','435135'),
	(5,'lucia','156723'),
	(6,'ariadna','810582');
	
delete from articulo;
insert into articulo(idArticulo,titulo,primerAutor,estado,resumen,palabrasClave,ficheroFuente,cartaPresentacion,CV,firma,revisado) values 
	(7,'Economia Española','antonio','con el editor', 'La economia española esta en decadencia', 'economia, euro', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, false),
	(8,'Gasol se retira','lucia','en revision', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, true),
	(9,'La caida de Facebook','ariadna','aceptado', 'Se caen los servidores de Facebook durante 6 horas con perdidas multimillonarias', 'facebook, red, caida', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, false),
	(10,'Poesia clasica','ariadna','publicado', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, false),
	(11,'El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, true);
	
delete from articulosDeAutores;
insert into articulosDeAutores(idArticulo, idAutor) values
	(7,4),
	(8,5),
	(8,4),
	(9,6),
	(10,6),
	(11,6);
