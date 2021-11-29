--Datos para carga inicial de la base de datos

delete from sugerencias;
insert into sugerencias (idArticulo, idRevisor) values
    ('16', '7d5c8f94-9e98-4ee5-bb8b-1e86d3461505'),
    ('9', 'c795be4d-448e-4faa-b788-39d96b4577a9'),
    ('13', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4'),
    ('16', 'bb6b1a56-70d2-4512-97fc-bcb5cb3fa468');
	
delete from revisores;
insert into revisores(idRevisor,nombre,estado, correo, especialidad) values 
	('de844393-18b4-47e9-8f4b-810e6d7223de','felipe','NO DISPONIBLE','felipe@uniovi.es', 'Violencia'),
	('fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4','ernesto','NO DISPONIBLE', 'ernesto@uniovi.es', 'Bases de datos'),
	('86671e22-02f6-4fb2-ba2d-e5c5e0155227','maria','NO DISPONIBLE', 'mperez@uniovi.es', 'Biologia'),
	('c795be4d-448e-4faa-b788-39d96b4577a9','carmen', 'NO DISPONIBLE', 'carmen@uniovi.es', 'Economia'),
	('043367e8-9752-49d2-b2ca-a217023c363c','pablo','NO DISPONIBLE', 'peibol@uniovi.es', 'Futbol'),
	('52934766-3f57-4a1c-a67e-d30d45c2975b','julio','NO DISPONIBLE', 'jfer@uniovi.es', 'Meses'),
	('26e7b09f-96dc-402b-9315-1fc844d6da9b','ruben','DISPONIBLE', 'rub@uniovi.es','Comida'),
	('bb6b1a56-70d2-4512-97fc-bcb5cb3fa468', 'oscar', 'DISPONIBLE', 'oscar@uniovi.es', 'Lesiones'),
	('7d5c8f94-9e98-4ee5-bb8b-1e86d3461505', 'pepe', 'SUGERIDO', 'pepe@pravia.es', 'Quesos');
	
delete from autores;
insert into autores(idAutor,nombre,dni) values 
	('8b021e87-4868-49e7-bea3-c5f85674fb59','antonio','435135'),
	('183add42-5d3e-40b1-9200-23bab1f65c02','lucia','156723'),
	('131c79b1-dc04-4a13-8325-5e480bcd3c86','ariadna','810582'),
	('1239272a-774c-4cad-a169-090df09f220f', 'hugo', '364786');
	
delete from articulosDeAutores;
insert into articulosDeAutores(idArticulo, idAutor) values
	(7,4),
	(8,5),
	(8,4),
	(9,6),
	(10,6),
	(11,6),
	(12,4);
insert into articulos(idArticulo,titulo,primerAutor,estado,resumen,palabrasClave,ficheroFuente,cartaPresentacion,CV, firma, vecesRevisado, versionDefinitiva) values 
	('7','Economia Española','antonio','pendiente', 'La economia española esta en decadencia', 'economia, euro', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	('8','Gasol se retira','lucia','en debate', 'Gasol anuncia su retirada del baloncesto', 'gasol, leyenda, Barca', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	('9','La caida de Facebook','ariadna','aceptado', 'Se caen los servidores de Facebook durante 6 horas con perdidas multimillonarias', 'facebook, red, caida', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	('10','Poesia clasica','ariadna','publicado', 'explicacion sobre la poesia clasica', 'poesia, españa', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', true, 1, true),
	('11','El Quijote, la obra maestra','ariadna','aceptado', 'análisis profundo sobre El Quijote', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	('12','Xavi será nuevo técnico del Barcelona','antonio','pendiente', 'Xavi será nuevo DT', 'fútbol, leyenda, Xavi, Barcelona', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	('13','Nuevo Articulo','lucia','con el editor', 'algo', 'literatura, historia', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	('14','Volcan de la Palma','hugo','en revision', 'el volcan no cesa', 'geología, volcan', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	('15','La luz no para de subir','ariadna','en revision', 'otro máximo histórico de la luz en España', 'luz, máximo, economía', 'FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 1, false),
	('16','Violencia en los colegios', 'lucia','con el editor','la violecia es cada vez más preocupente en los colegios','violencia, colegios','FicheroFuente.pdf', 'Presentacion.pdf', 'cv.pdf', false, 0, false),
	('17','Hola','hugo','borrador','','','','','',false, 0, false),
		
	('101', 'Articulo sin asginar para rechazar', 'Hugo', 'con el editor', 'si', 'no', 'fichero', 'presentacion', 'cv', false, 0, false);

delete from autoresSecundarios;
insert into autoresSecundarios(idArticulo, idAutor, tipoAutor) values
	('7','8b021e87-4868-49e7-bea3-c5f85674fb59', 'PRINCIPAL'),
	('8','183add42-5d3e-40b1-9200-23bab1f65c02', 'PRINCIPAL'),
	('8','8b021e87-4868-49e7-bea3-c5f85674fb59', 'SECUNDARIO'),
	('9','131c79b1-dc04-4a13-8325-5e480bcd3c86', 'PRINCIPAL'),
	('10','131c79b1-dc04-4a13-8325-5e480bcd3c86', 'PRINCIPAL'),
	('11','131c79b1-dc04-4a13-8325-5e480bcd3c86', 'PRINCIPAL'),
	('12','8b021e87-4868-49e7-bea3-c5f85674fb59', 'PRINCIPAL'),
	('13','183add42-5d3e-40b1-9200-23bab1f65c02', 'PRINCIPAL'),
	('17','1239272a-774c-4cad-a169-090df09f220f', 'PRINCIPAl'),
	('16','183add42-5d3e-40b1-9200-23bab1f65c02', 'PRINCIPAL'),
	('101','1239272a-774c-4cad-a169-090df09f220f', 'PRINCIPAL'),
	('14','1239272a-774c-4cad-a169-090df09f220f', 'PRINCIPAL'),
	('15','131c79b1-dc04-4a13-8325-5e480bcd3c86', 'PRINCIPAL');
	
delete from revisiones;
insert into revisiones(idArticulo, idRevisor, numeroRevision, fecha, comentariosAutor, comentariosEditor, decision, enviarAlEditor, estadoDeLaPropuesta) values
	('10', 'de844393-18b4-47e9-8f4b-810e6d7223de', 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar', true, 'ACEPTADA'),
	('10', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'rechazar', true, 'ACEPTADA'),
	('10', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar con cambios mayores', true, 'ACEPTADA'),
	('8', 'de844393-18b4-47e9-8f4b-810e6d7223de', 1,'31/12/2021', 'No me esta gustando','No aceptaria','rechazar',true, 'ACEPTADA'),
	('8', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 1,'31/12/2021', 'Me esta gustando','Aceptaria','aceptar con cambios menores',true, 'ACEPTADA'),
	('8', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 1,'31/12/2021', 'Articulo normal','Se puede aceptar','aceptar',true, 'ACEPTADA'),
	('7', 'de844393-18b4-47e9-8f4b-810e6d7223de', 1,'31/12/2021', '','','',false, 'ACEPTADA'),
	('7', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 1,'31/12/2021', '','','',false, 'ACEPTADA'),
	('7', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 1,'31/12/2021', '','','',false, 'PENDIENTE'),
	('11', 'c795be4d-448e-4faa-b788-39d96b4577a9', 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar con cambios menores', true, 'ACEPTADA'),
	('11', '043367e8-9752-49d2-b2ca-a217023c363c', 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', true, 'ACEPTADA'),
	('11', '52934766-3f57-4a1c-a67e-d30d45c2975b', 0,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar', true, 'ACEPTADA'),
	('12', 'de844393-18b4-47e9-8f4b-810e6d7223de', 1,'10/12/2021', '', '', '', false, 'PENDIENTE'),
	('12', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 1,'10/12/2021', '', '', '', false, 'PENDIENTE'),
	('12', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 1,'10/12/2021', '', '', '', false, 'PENDIENTE'),
	('13', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 1,'25/12/2021', '', '', '', false, 'ACEPTADA'),
	('14', 'de844393-18b4-47e9-8f4b-810e6d7223de', 1,'31/12/2021', 'Muy buen artículo', 'Debe ser publicado', 'aceptar con cambios menores', true, 'ACEPTADA'),
	('14', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 1,'31/12/2021', 'Mejorable', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', false, 'ACEPTADA'),
	('14', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 1,'31/12/2021', 'Buen artículo', 'Aceptable', 'aceptar', true, 'ACEPTADA'), 
	('15', 'de844393-18b4-47e9-8f4b-810e6d7223de', 1,'31/12/2021', 'Mal artículo', 'esto no se puede publicar', 'rechazar', true, 'ACEPTADA'),
	('15', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 1,'31/12/2021', 'Artículo regular', 'Tengo mis dudas de aceptarlo', 'aceptar con cambios mayores', true, 'ACEPTADA'),
	('15', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 1,'31/12/2021', 'No tiene ni pies ni cabeza', 'Sin comentarios, es malísimo', 'rechazar', true, 'ACEPTADA'),
	('15', 'de844393-18b4-47e9-8f4b-810e6d7223de', 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar', 'aceptar', true, 'ACEPTADA'),
	('15', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar sin lugar a dudas', 'aceptar', true, 'ACEPTADA'),
	('15', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 2,'31/12/2021', 'Es increíble lo que ha mejorado', 'publicar sin lugar a dudas', 'aceptar', false, 'ACEPTADA'),
	('16', 'fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 1,'31/12/2021', '', '', '', false, 'RECHAZADA'),
	('16', 'de844393-18b4-47e9-8f4b-810e6d7223de', 2,'31/12/2021', '', '', '', false, 'ACEPTADA'),
	('16', '86671e22-02f6-4fb2-ba2d-e5c5e0155227', 2,'31/12/2021', '', '', '', false, 'PENDIENTE'); 
	
delete from usuarios;
insert into usuarios (idUsuario, nombre, tipoUsuario) values
	('0', 'Alex', 'Editor'),
	('de844393-18b4-47e9-8f4b-810e6d7223de', 'felipe', 'Revisor'),
	('fa46f0fc-ef50-4a9e-9e35-6659dd39e6a4', 'ernesto', 'Revisor'),
	('86671e22-02f6-4fb2-ba2d-e5c5e0155227','maria', 'Revisor'),
	('c795be4d-448e-4faa-b788-39d96b4577a9','carmen', 'Revisor'),
	('043367e8-9752-49d2-b2ca-a217023c363c','pablo', 'Revisor'),
	('52934766-3f57-4a1c-a67e-d30d45c2975b','julio','Revisor'),
	('26e7b09f-96dc-402b-9315-1fc844d6da9b','ruben', 'Revisor'),
	('bb6b1a56-70d2-4512-97fc-bcb5cb3fa468', 'oscar', 'Revisor'),
	('8b021e87-4868-49e7-bea3-c5f85674fb59','antonio', 'Autor'),
	('183add42-5d3e-40b1-9200-23bab1f65c02','lucia', 'Autor'),
	('131c79b1-dc04-4a13-8325-5e480bcd3c86','ariadna', 'Autor'),
	('1239272a-774c-4cad-a169-090df09f220f', 'hugo', 'Autor');
	
delete from Debates;
insert into Debates (idDebate, idArticulo, fecha, abierto) values
	('1', '8', '15/02/2022', true);
