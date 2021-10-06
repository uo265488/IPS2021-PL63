package giis.demo.util;

import java.util.ArrayList;
import java.util.List;

import giis.demo.tkrun.revisor.RevisorDto;
import giis.demo.tkrun.revisor.RevisorEntity;

public class EntityAssembler {

	
	public static List<RevisorEntity> toRevisorEntityList(List<RevisorDto> revisores) {
		List<RevisorEntity> ents = new ArrayList<>();
		for (RevisorDto rev : revisores) {
			ents.add(toRevisorEntity(rev));
		}
		return ents;
	}

	private static RevisorEntity toRevisorEntity(RevisorDto rev) {
		RevisorEntity ent = new RevisorEntity();
		
		ent.setEstado(rev.getEstado());
		ent.setNombre(rev.getNombre());
		ent.setId(rev.getId());
		
		
		return ent;
	}

	
}
