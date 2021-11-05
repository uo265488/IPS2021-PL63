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
insert into revisiones(idArticulo, idRevisor, fecha, comentariosAutor, comentariosEditor, decision, enviarAlEditor, estadoDeLaPropuesta) values
	(10, 1, '2020-10-2','Muy buen artículo', 'Debe ser publicado', 'altamente recomendable aceptar', true, 'ACEPTADA'),
	(10, 2, '2020-10-2','Mejorable', 'Tengo mis dudas de aceptarlo', 'poco recomendable aceptar', true, 'ACEPTADA'),
	(10, 3, '2020-10-2','Buen artículo', 'Aceptable', 'aceptardo', true, 'RECHAZADA'),
	(8, 1, '2020-10-2','Me esta gustando','Aceptaria','altamente recomendable aceptar',true, 'ACEPTADA'),
	(8, 2, '2020-10-2','Me esta gustando','Aceptaria','altamente recomendable aceptar',true, 'RECHAZADA'),
	(8, 3,'2020-10-2', 'Me esta gustando','Aceptaria','altamente recomendable aceptar',true, 'PENDIENTE'),
	(7, 3, '2020-10-2','Me esta gustando mucho','Aceptaria','altamente recomendable aceptar',false, 'RECHAZADA'),
	(11, 4, '2020-10-2','Muy buen artículo', 'Debe ser publicado', 'altamente recomendable aceptar', true, 'ACEPTADA'),
	(11, 5,'2020-10-2', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'poco recomendable aceptar', true, 'PENDIENTE'),
	(11, 6, '2020-10-2','Buen artículo', 'Aceptable', 'aceptardo', true, 'RECHAZADA');
	
delete from usuarios;
insert into usuarios (idUsuario, nombre, tipoUsuario) values
	(1, 'Alex', 'Editor'),
	(2, 'Hugo', 'Autor'),
	(3, 'Oscar', 'Revisor');
	

