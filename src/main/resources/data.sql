--Datos para carga inicial de la base de datos
delete from carreras;
insert into carreras(id,inicio,fin,fecha,descr) values 
	(100,'2016-10-05','2016-10-25','2016-11-09','finalizada'),
	(101,'2016-10-05','2016-10-25','2016-11-10','en fase 3'),
	(102,'2016-11-05','2016-11-09','2016-11-20','en fase 2'),
	(103,'2016-11-10','2016-11-15','2016-11-21','en fase 1'),
	(104,'2016-11-11','2016-11-15','2016-11-22','antes inscripcion');
	
delete from revisores;
insert into revisores(id,nombre,estado) values 
	(1,'felipe','disponible'),
	(2,'ernesto','disponible'),
	(3,'maria','no disponible');
	
delete from usuarios;
insert into usuarios(nombre,tipoUsuario) values
	('alex', 'autor'),
	('oscar','revisor'),
	('javi', 'editor');
	
delete from articulos;
insert into articulos values 
	('1', 'Ciencia', 'Alex', 'Oscar','borrador', 'Articulo sobre ciencia en general','Ciencia', 'ficheroFuente.docx', 'cartaPresentacionAlex.docx', 'cvAlex.docx', 'firmaAlex.docx'),
	('2', 'Biología', 'Javi', 'Oscar', 'enviado', 'Articulo sobre biología', 'Biología', 'ficheroFuente.pdf', 'cartaPresentacionJavi.pdf', 'cvJavi.pdf', 'firmaJavi.pdf'),
	('3', 'Informática', 'Hugo', 'Alex', 'enviado', 'Artículo sobre informática', 'Informática', 'ficheroFuente.txt', 'cartaPresentacionHugo.txt', 'cvHugo.txt', 'firmaHugo.txt');
