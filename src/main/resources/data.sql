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
	(6,'ariadna','810582'),
	(2, 'Hugo', '3647867');
	
delete from articulos;
insert into articulos(idArticulo,titulo,primerAutor,estado,resumen,palabrasClave,ficheroFuente,cartaPresentacion,CV, firma, vecesRevisado, versionDefinitiva) values 
	(7,'Economia Española','antonio','en revision', 'La economia española esta en decadencia', 'economia, euro', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(8,'Gasol se retira','lucia','con el editor', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(9,'La caida de Facebook','ariadna','aceptado', 'Se caen los servidores de Facebook durante 6 horas con perdidas multimillonarias', 'facebook, red, caida', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(10,'Poesia clasica','ariadna','publicado', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, 1, true),
	(11,'El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(12,'Xavi será nuevo técnico del Barcelona','antonio','pendiente', 'Xavi será nuevo DT', 'fútbol, leyenda, Xavi, Barcelona', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(13,'Nuevo Articulo','lucia','pendiente', 'algo', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(14,'Volcán de la Palma, el porqué','lucia','en revision', 'razones de la explosión del volcán', 'geología, Palma', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(15,'La luz no para de subir','lucia','en revision', 'la luz alcanza otro máximo histórico', 'luz, economía', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false);
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
insert into revisiones(idArticulo, idRevisor, numeroRevision, fecha, comentariosAutor, comentariosEditor, decision, enviarAlEditor, estadoDeLaPropuesta) values
	(10, 1, 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar', true, 'ACEPTADO'),
	(10, 2, 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'rechazar', true, 'ACEPTADO'),
	(10, 3, 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar con cambios mayores', true, 'ACEPTADO'),
	(8, 1, 1,'31/12/2021', 'No me esta gustando','No aceptaria','rechazar',true, 'ACEPTADO'),
	(8, 2, 1,'31/12/2021', 'Me esta gustando','Aceptaria','aceptar con cambios menores',true, 'ACEPTADO'),
	(8, 3, 1,'31/12/2021', 'Articulo normal','Se puede aceptar','aceptar',true, 'ACEPTADO'),
	(8, 3, 2,'31/12/2021', '','','',false, 'ACEPTADO'),
	(7, 3, 1,'31/12/2021', 'Me esta gustando mucho','Aceptaria','aceptar con cambios menores',false, 'ACEPTADO'),
	(11, 4, 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar con cambios menores', true, 'ACEPTADO'),
	(11, 5, 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', true, 'ACEPTADO'),
	(11, 6, 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar', true, 'ACEPTADO'),
	(11, 6, 2,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar', true, 'ACEPTADO'),
	(12, 3, 1,'10/12/2021', '', '', '', false, 'PENDIENTE'),
	(13, 3, 1,'25/12/2021', '', '', '', false, 'PENDIENTE'),
	(14, 1, 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar con cambios menores', true, 'ACEPTADO'),
	(14, 2, 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', false, 'ACEPTADO'),
	(14, 3, 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar', true, 'ACEPTADO'), 
	(15, 1, 1,'31/12/2021', 'Mal artículo', 'esto no se puede publicar', 'rechazar', true, 'ACEPTADO'),
	(15, 2, 1,'31/12/2021', 'Artículo regular', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', true, 'ACEPTADO'),
	(15, 3, 1,'31/12/2021', 'No tiene ni pies ni cabeza', 'Sin comentarios, es malísimo', 'rechazar', true, 'ACEPTADO'),
	(15, 1, 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar', 'aceptar', true, 'ACEPTADO'),
	(15, 2, 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar sin lugar a dudas', 'aceptar', false, 'ACEPTADO'),
	(15, 3, 2,'31/12/2021', '', '', '', false, 'ACEPTADO');
	
delete from usuarios;
insert into usuarios (idUsuario, nombre, tipoUsuario) values
	(1, 'Alex', 'Editor'),
	(2, 'Hugo', 'Autor'),
	(3, 'Oscar', 'Revisor');;
