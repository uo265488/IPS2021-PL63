--Datos para carga inicial de la base de datos
	
delete from revisores;
insert into revisores(idRevisor,nombre,estado) values 
	(1,'felipe','disponible'),
	(2,'ernesto','disponible'),
	(3,'maria','no disponible'),
	(4,'carmen', 'no disponible'),
	(5,'pablo','no disponible'),
	(6,'julio','no disponible'),
	(7,'ruben','disponible');
	
delete from autores;
insert into autores(idAutor,nombre,dni) values 
	(4,'antonio','435135'),
	(5,'lucia','156723'),
	(6,'ariadna','810582');
	
delete from articulos;
insert into articulos(idArticulo,titulo,primerAutor,estado,resumen,palabrasClave,ficheroFuente,cartaPresentacion,CV, firma, vecesRevisado, versionDefinitiva) values 
	(7,'Economia Española','antonio','en revision', 'La economia española esta en decadencia', 'economia, euro', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(8,'Gasol se retira','lucia','con el editor', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(9,'La caida de Facebook','ariadna','aceptado', 'Se caen los servidores de Facebook durante 6 horas con perdidas multimillonarias', 'facebook, red, caida', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(10,'Poesia clasica','ariadna','publicado', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, 1, true),
	(11,'El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(12,'Xavi será nuevo técnico del Barcelona','antonio','con el editor', 'Xavi será nuevo DT', 'fútbol, leyenda, Xavi, Barcelona', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(13,'Nuevo Articulo','lucia','con el editor', 'algo', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false);
	
delete from articulosDeAutores;
insert into articulosDeAutores(idArticulo, idAutor) values
	(7,4),
	(8,5),
	(8,4),
	(9,6),
	(10,6),
	(11,6),
	(12,4),
	(13,5);
	
delete from revisiones;
insert into revisiones(idArticulo, idRevisor, comentariosAutor, comentariosEditor, decision, enviarAlEditor, decisionArticulo, decisionTomada) values
	(10, 1, 'Muy buen artículo', 'Debe ser publicado', 'aceptar', true, true, true),
	(10, 2, 'Mejorable', 'Tengo mis dudas de aceptarlo', 'rechazar', true, true, true),
	(10, 3, 'Buen artículo', 'Aceptable', 'aceptar con cambios mayores', true, true, true),
	(8, 1, 'No me esta gustando','No aceptaria','rechazar',true, true, true),
	(8, 2, 'Me esta gustando','Aceptaria','aceptar con cambios menores',true, true, true),
	(8, 3, 'Articulo normal','Se puede aceptar','aceptar',true, true, true),
	(7, 3, 'Me esta gustando mucho','Aceptaria','aceptar con cambios menores',false, true, true),
	(11, 4, 'Muy buen artículo', 'Debe ser publicado', 'aceptar con cambios menores', true, true, true),
	(11, 5, 'Mejorable', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', true, true, true),
	(11, 6, 'Buen artículo', 'Aceptable', 'aceptar', true, true, true),
	(12, 3, '', '', '', false, false, false),
	(13, 3, '', '', '', false, false, false);
	
delete from usuarios;
insert into usuarios (idUsuario, nombre, tipoUsuario) values
	(1, 'Alex', 'Editor'),
	(2, 'Hugo', 'Autor'),
	(3, 'Oscar', 'Revisor');
