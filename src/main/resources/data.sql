--Datos para carga inicial de la base de datos

delete from sugerencias;
insert into sugerencias (idArticulo, idRevisor) values
    (20, 20),  
    (20, 21),
    (22, 22);
	
delete from revisores;
insert into revisores(idRevisor,nombre,estado, correo, especialidad) values 
	(1,'felipe','disponible', 'uo282362@uniovi.es', 'Biologia' ),
	(2,'ernesto','disponible', 'uo232322@uniovi.es', 'Matemáticas' ),
	(3,'maria','disponible', 'uo0198232@uniovi.es', 'Ciencia' ),
	(4,'carmen', 'disponible', 'uo092843@uniovi.es', 'Prensa Rosa' ),
	(5,'pablo','disponible', 'uo111111@uniovi.es', 'Películas' ),
	(6,'julio','disponible', 'pepe@uniovi.es', 'Series' ),
	(7,'ruben','no disponible', 'uo2093029@uniovi.es', 'Geografía' ),
	(20,'Paco','sugerido', 'uo282362@uniovi.es', 'Osos' ),
	(21,'Carlos','sugerido', 'uo2834552@uniovi.es', 'Dormir' ),
	(22,'Franchesco','disponible', 'uo333333@uniovi.es', 'Comida' );
	
delete from autores;
insert into autores(idAutor,nombre,dni) values 
	(4,'antonio','435135'),
	(5,'lucia','156723'),
	(6,'ariadna','810582');
	
delete from articulos;
insert into articulos(idArticulo,titulo,primerAutor,estado,resumen,palabrasClave,ficheroFuente,cartaPresentacion,CV,firma, vecesRevisado, versionDefinitiva) values 
	(7,'Economia Española','antonio','en revision', 'La economia española esta en decadencia', 'economia, euro', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(8,'Gasol se retira','lucia','con el editor', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(2,'Alex se retira de ips','lucia','con el editor', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	(9,'La caida de Facebook','ariadna','aceptado', 'Se caen los servidores de Facebook durante 6 horas con perdidas multimillonarias', 'facebook, red, caida', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(10,'Poesia clasica','ariadna','publicado', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, 1, true),
	(11,'El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(12,'Teo va a la escuela','ariadna','rechazado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(20,'Poesia clasica','ariadna','con el editor', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, 1, true),
	(21,'El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	(22,'Teo va a la escuela','ariadna','rechazado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false);
	
delete from autoresSecundarios;
insert into autoresSecundarios(idArticulo, idAutor) values
	(7,4),
	(8,5),
	(8,4),
	(9,6),
	(10,6),
	(20,5),
	(20, 6);
	
delete from revisiones;
insert into revisiones(idArticulo, idRevisor, numeroRevision, fecha, comentariosAutor, comentariosEditor, decision, enviarAlEditor, estadoDeLaPropuesta) values
	(10, 1, 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar', true, 'ACEPTADO'),
	(10, 2, 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'rechazar', true, 'ACEPTADO'),
	(10, 3, 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar con cambios mayores', true, 'ACEPTADO'),
	(8, 1, 1,'31/12/2021', 'No me esta gustando','No aceptaria','rechazar',true, 'ACEPTADO'),
	(8, 2, 1,'31/12/2021', 'Me esta gustando','Aceptaria','aceptar con cambios menores',true, 'ACEPTADO'),
	(8, 3, 1,'31/12/2021', 'Articulo normal','Se puede aceptar','aceptar',true, 'ACEPTADO'),
--	(8, 3, 2,'31/12/2021', '','','',false, 'ACEPTADO'),
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
	(3, 'Oscar', 'Revisor');
	

