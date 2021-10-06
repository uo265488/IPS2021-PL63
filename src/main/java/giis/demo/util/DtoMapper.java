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

		dto.setId(rev.getId());
		dto.setNombre(rev.getNombre());
		dto.setEstado(rev.getEstado());

		return dto;
	}

	public static ArticuloDto toArticuloDto(ArticuloEntity articulo) {
		ArticuloDto dto = new ArticuloDto();

		dto.setCartaPresentacion(articulo.getCartaPresentacion());
		dto.setCVAutor(articulo.getCVAutor());
		dto.setEstado(articulo.getEstado());
		dto.setFicheroFuente(articulo.getFicheroFuente());
		dto.setFirma(dto.getFirma());
		dto.setId(articulo.getId());
		dto.setOtrosAutores(articulo.getOtrosAutores());
		dto.setPalabrasClave(articulo.getPalabrasClave());
		dto.setPrimerAutor(articulo.getPrimerAutor());
		dto.setResumen(articulo.getResumen());
		dto.setTitulo(articulo.getTitulo());

		return dto;
	}

	public static RevisionDto toRevisionDto(RevisorEntity rev, ArticuloEntity articulo, String fecha) {
		RevisionDto dto = new RevisionDto();
		
		dto.setRevisor(DtoMapper.toRevisorDto(rev));
		dto.setArticulo(DtoMapper.toArticuloDto(articulo));
		dto.setFecha(fecha);
		
		return dto;
		
	}

}
