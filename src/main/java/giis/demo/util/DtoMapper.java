package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;
import giis.demo.tkrun.controllers.entities.RevisorEntity;
import giis.demo.tkrun.models.dtos.ArticuloDto;
import giis.demo.tkrun.models.dtos.RevisionDto;
import giis.demo.tkrun.models.dtos.RevisorDto;

public class DtoMapper {

	public static List<RevisorDto> toRevisorDtoList(List<RevisorEntity> revisores) {
		List<RevisorDto> dtos = new ArrayList<>();
		for (RevisorEntity rev : revisores) {
			dtos.add(toRevisorDto(rev));
		}
		return dtos;
	}

	public static RevisorDto toRevisorDto(RevisorEntity rev) {
		RevisorDto dto = new RevisorDto();

		dto.setIdRevisor(rev.getId());
		dto.setNombre(rev.getNombre());
		dto.setEstado(rev.getEstado());

		return dto;
	}

	public static ArticuloDto toArticuloDto(ArticuloEntity articulo) {
		ArticuloDto dto = new ArticuloDto();

		dto.setCartaPresentacion(articulo.getCartaPresentacion());
		dto.setCV(articulo.getCV());
		dto.setEstado(articulo.getEstado());
		dto.setFicheroFuente(articulo.getFicheroFuente());
		dto.setFirma(dto.isFirma());
		dto.setIdArticulo(articulo.getIdArticulo());
		dto.setOtrosAutores(articulo.getOtrosAutores());
		dto.setPalabrasClave(articulo.getPalabrasClave());
		dto.setPrimerAutor(articulo.getPrimerAutor());
		dto.setResumen(articulo.getResumen());
		dto.setTitulo(articulo.getTitulo());
		dto.setVecesRevisado(articulo.getVecesRevisado());
		dto.setVersionDefinitiva(articulo.isVersionDefinitiva());

		return dto;
	}

	public static RevisionDto toRevisionDto(RevisorEntity rev, ArticuloEntity articulo, String fecha) {
		RevisionDto dto = new RevisionDto();

		dto.setIdArticulo(articulo.getIdArticulo());
		dto.setIdRevisor(rev.getId());
		dto.setFecha(fecha);

		return dto;

	}

}
