package giis.demo.util.articulo;

import java.util.Comparator;

import giis.demo.tkrun.controllers.entities.ArticuloEntity;

public class ArticuloComparatorTitulo implements Comparator<ArticuloEntity> {

	@Override
	public int compare(ArticuloEntity articulo1, ArticuloEntity articulo2) {

		int diff = articulo1.getTitulo().compareTo(articulo2.getTitulo());

		return diff;
	}

}
