package giis.demo.tkrun.models.articulo;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.util.Database;

public class ArticuloModel {

	private Database db = new Database();

	/**
	 * Actualizar articulo en funcion del id
	 * 
	 * @param articuloDto
	 */
	public void update(ArticuloDto articuloDto) {
		// validaciones (en este caso nada)
		String sql = "update articulos set cartaPresentacion = ?, CV = ?, estado=?, ficheroFuente = ?, otrosAutores=?, palabrasClave=?, primerAutor=?, resumen=?, titulo=?, vecesRevisado=?, firma=?, versionDefinitiva=? where idArticulo = ?";

		db.executeUpdate(sql, articuloDto.getCartaPresentacion(), articuloDto.getCV(), articuloDto.getEstado(),
				articuloDto.getFicheroFuente(), articuloDto.getOtrosAutores(), articuloDto.getPalabrasClave(),
				articuloDto.getPrimerAutor(), articuloDto.getResumen(), articuloDto.getTitulo(),
				articuloDto.getVecesRevisado(), articuloDto.isFirma(), articuloDto.isVersionDefinitiva(),
				articuloDto.getIdArticulo());

	}

	/**
	 * Listado de los articulos nuevos
	 * 
	 * @return
	 */
	public List<ArticuloDto> listArticulosNuevos() {
		// validaciones (en este caso nada)
		String sql = "select * from articulos where estado=?";

		return db.executeQueryPojo(ArticuloDto.class, sql, "nuevo");
	}

//	/**
//	 * Borrar articulo en funcion del id
//	 * @param articuloDto
//	 */
//	public void delete(ArticuloDto articuloDto) {
//		String sql = "delete from articulos where id=?";
//		
//		db.executeQueryPojo(ArticuloDto.class, sql, articuloDto.getId());
//	}

	/**
	 * Obtiene la lista de articulos que han sido enviados por los autores.
	 * 
	 * @return
	 */
	public List<ArticuloDto> getArticulos() {
		String sql = "SELECT * from articulos where estado <> 'borrador'";

		return db.executeQueryPojo(ArticuloDto.class, sql);
	}

	/**
	 * Obtiene la lista de articulos que deben ser evaluados por el editor
	 */
	public List<ArticuloDto> getArticulosTomarDecision(){
		String sql = "SELECT idArticulo from articulos where vecesRevisado <= 1 and estado = 'con el editor'";
		List<ArticuloDto> idsArticulos = db.executeQueryPojo(ArticuloDto.class, sql);
		
		List<RevisionDto> infoArtRevisados = new ArrayList<RevisionDto>();
		List<Integer> idsArticulosRevisados = new ArrayList<Integer>();
		
		for(ArticuloDto str: idsArticulos) {
			sql = "SELECT * from revisiones where idArticulo = ?";
			infoArtRevisados = db.executeQueryPojo(RevisionDto.class, sql, str.getIdArticulo());
			if(infoArtRevisados.size() == 3) {
				boolean estaRevisado = true;
				for(RevisionDto revision: infoArtRevisados) {
					if(!revision.isEnviarAlEditor()) {
						estaRevisado = false;
						break;
					}
				}
				if(estaRevisado)
					idsArticulosRevisados.add(infoArtRevisados.get(0).getIdArticulo());
			}
		}
		
		sql = "SELECT idArticulo, titulo, primerAutor from articulos where idArticulo = ?";
		
		idsArticulos.clear();
		for(int id: idsArticulosRevisados) {
			idsArticulos.add((ArticuloDto) db.executeQueryPojo(ArticuloDto.class, sql, id));
		}
		
		return idsArticulos;
	}


}
