--Datos para carga inicial de la base de datos

delete from sugerencias;
insert into sugerencias (idArticulo, idRevisor) values
    (16, 13),
    (9, 4),
    (13, 2),
    (16, 12);
	
delete from revisores;
insert into revisores(idRevisor,nombre,estado, correo, especialidad) values 
	(1,'felipe','NO DISPONIBLE','felipe@uniovi.es', 'Violencia'),
	(2,'ernesto','NO DISPONIBLE', 'ernesto@uniovi.es', 'Bases de datos'),
	(3,'maria','NO DISPONIBLE', 'mperez@uniovi.es', 'Biologia'),
	(4,'carmen', 'NO DISPONIBLE', 'carmen@uniovi.es', 'Economia'),
	(5,'pablo','NO DISPONIBLE', 'peibol@uniovi.es', 'Futbol'),
	(6,'julio','NO DISPONIBLE', 'jfer@uniovi.es', 'Meses'),
	(7,'ruben','DISPONIBLE', 'rub@uniovi.es','Comida'),
	(12, 'Oscar', 'DISPONIBLE', 'oscar@uniovi.es', 'Lesiones'),
	(13, 'Pepe', 'SUGERIDO', 'pepe@pravia.es', 'Quesos');
	
delete from autores;
insert into autores(idAutor,nombre,dni) values 
	(8,'antonio','435135'),
	(9,'lucia','156723'),
	(10,'ariadna','810582'),
	(11, 'hugo', '364786');
	
delete from articulos;
insert into articulos(idArticulo,titulo,primerAutor,estado,resumen,palabrasClave,ficheroFuente,cartaPresentacion,CV, firma, vecesRevisado, versionDefinitiva, pendienteDeCambios) values 
	(7,'Economia Española','antonio','pendiente', 'La economia española esta en decadencia', 'economia, euro', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false, false),
	(8,'Gasol se retira','lucia','aceptado con cambios menores', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false, true),
	(9,'La caida de Facebook','ariadna','aceptado', 'Se caen los servidores de Facebook durante 6 horas con perdidas multimillonarias', 'facebook, red, caida', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false, false),
	(10,'Poesia clasica','ariadna','publicado', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, 1, true, false),
	(11,'El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false, false),
	(12,'Xavi será nuevo técnico del Barcelona','antonio','pendiente', 'Xavi será nuevo DT', 'fútbol, leyenda, Xavi, Barcelona', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false, false),
	(13,'Nuevo Articulo','lucia','con el editor', 'algo', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false, false),
	(14,'Volcan de la Palma','hugo','en revision', 'el volcan no cesa', 'geología, volcan', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false, false),
	(15,'La luz no para de subir','ariadna','en revision', 'otro máximo histórico de la luz en España', 'luz, máximo, economía', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false, false),
	(16,'Violencia en los colegios', 'lucia','con el editor','la violecia es cada vez más preocupente en los colegios','violencia, colegios','FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false, false),
	(17,'Hola','hugo','borrador','','','','','',false, 0, false, false),
	(101, 'Articulo sin asginar para rechazar', 'Hugo', 'con el editor', 'si', 'no', 'fichero', 'presentacion', 'cv', false, 0, false, false);

delete from autoresSecundarios;
insert into autoresSecundarios(idArticulo, idAutor) values
	(7,8),
	(8,9),
	(8,8),
	(9,10),
	(10,10),
	(11,10),
	(12,8),
	(13,9),
	(17, 11),
	(16,9),
	(101,11),
	(14,11),
	(15,10);
	
delete from revisiones;
insert into revisiones(idArticulo, idRevisor, numeroRevision, fecha, comentariosAutor, comentariosEditor, decision, enviarAlEditor, estadoDeLaPropuesta) values
	(10, 1, 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar', true, 'ACEPTADA'),
	(10, 2, 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'rechazar', true, 'ACEPTADA'),
	(10, 3, 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar con cambios mayores', true, 'ACEPTADA'),
	(8, 1, 1,'31/12/2021', 'No me esta gustando','No aceptaria','rechazar',true, 'ACEPTADA'),
	(8, 2, 1,'31/12/2021', 'Me esta gustando','Aceptaria','aceptar con cambios menores',true, 'ACEPTADA'),
	(8, 3, 1,'31/12/2021', 'Articulo normal','Se puede aceptar','aceptar',true, 'ACEPTADA'),
	(7, 1, 1,'31/12/2021', '','','',false, 'ACEPTADA'),
	(7, 2, 1,'31/12/2021', '','','',false, 'ACEPTADA'),
	(7, 3, 1,'31/12/2021', '','','',false, 'PENDIENTE'),
	(11, 4, 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar con cambios menores', true, 'ACEPTADA'),
	(11, 5, 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', true, 'ACEPTADA'),
	(11, 6, 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar', true, 'ACEPTADA'),
	(12, 1, 1,'10/12/2021', '', '', '', false, 'PENDIENTE'),
	(12, 2, 1,'10/12/2021', '', '', '', false, 'PENDIENTE'),
	(12, 3, 1,'10/12/2021', '', '', '', false, 'PENDIENTE'),
	(13, 3, 1,'25/12/2021', '', '', '', false, 'ACEPTADA'),
	(14, 1, 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar con cambios menores', true, 'ACEPTADA'),
	(14, 2, 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', false, 'ACEPTADA'),
	(14, 3, 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar', true, 'ACEPTADA'), 
	(15, 1, 1,'31/12/2021', 'Mal artículo', 'esto no se puede publicar', 'rechazar', true, 'ACEPTADA'),
	(15, 2, 1,'31/12/2021', 'Artículo regular', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', true, 'ACEPTADA'),
	(15, 3, 1,'31/12/2021', 'No tiene ni pies ni cabeza', 'Sin comentarios, es malísimo', 'rechazar', true, 'ACEPTADA'),
	(15, 1, 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar', 'aceptar', true, 'ACEPTADA'),
	(15, 2, 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar sin lugar a dudas', 'aceptar', true, 'ACEPTADA'),
	(15, 3, 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar sin lugar a dudas', 'aceptar', false, 'ACEPTADA'),
	(16, 2, 1,'31/12/2021', '', '', '', false, 'RECHAZADA'),
	(16, 1, 2,'31/12/2021', '', '', '', false, 'ACEPTADA'),
	(16, 3, 2,'31/12/2021', '', '', '', false, 'PENDIENTE');
	
delete from usuarios;
insert into usuarios (idUsuario, nombre, tipoUsuario) values
	(0, 'Alex', 'Editor'),
	(1, 'felipe', 'Revisor'),
	(2, 'ernesto', 'Revisor'),
	(3,'maria', 'Revisor'),
	(4,'carmen', 'Revisor'),
	(5,'pablo', 'Revisor'),
	(6,'julio','Revisor'),
	(7,'ruben', 'Revisor'),
	(12, 'Oscar', 'Revisor'),
	(8,'antonio', 'Autor'),
	(9,'lucia', 'Autor'),
	(10,'ariadna', 'Autor'),
	(11, 'Hugo', 'Autor');
