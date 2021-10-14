--Datos para carga inicial de la base de datos
	
delete from revisores;
insert into revisores(idRevisor,nombre,estado) values 
	(1,'felipe','disponible'),
	(2,'ernesto','disponible'),
	(3,'maria','no disponible');
	
delete from autores;
insert into autores(idAutor,nombre,dni) values 
	(4,'antonio','435135'),
	(5,'lucia','156723'),
	(6,'ariadna','810582');
	
delete from Articulos;
insert into Articulos(idArticulo,titulo,primerAutor,estado,resumen,palabrasClave,ficheroFuente,cartaPresentacion,CV,firma, vecesRevisado, versionDefinitiva) values 
	(7,'Economia Española','antonio','con el editor', 'La economia española esta en decadencia', 'economia, euro', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(8,'Gasol se retira','lucia','en revision', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(9,'La caida de Facebook','ariadna','aceptado', 'Se caen los servidores de Facebook durante 6 horas con perdidas multimillonarias', 'facebook, red, caida', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(10,'Poesia clasica','ariadna','publicado', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, 1, true),
	(11,'El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false);
	
delete from articulosDeAutores;
insert into articulosDeAutores(idArticulo, idAutor) values
	(7,4),
	(8,5),
	(8,4),
	(9,6),
	(10,6),
	(11,6);
	
delete from revisiones;
insert into revisiones(idArticulo, idRevisor, comentariosAutor, comentariosEditor, decision, enviarAlEditor) values
	(10, 1, 'Muy buen artículo', 'Debe ser publicado', 'altamente recomendable aceptar', true),
	(10, 2, 'Mejorable', 'Tengo mis dudas de aceptarlo', 'poco recomendable aceptar', true),
	(10, 3, 'Buen artículo', 'Aceptable', 'aceptardo', true),
	(8, 1, 'Me esta gustando','','',false);
