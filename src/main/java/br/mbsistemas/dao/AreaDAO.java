package br.mbsistemas.dao;

import javax.persistence.TypedQuery;

import br.mbsistemas.model.Area;

public class AreaDAO extends HibernateDAO<Area>{

	public Area buscarArea(String endereco) {
		this.conectar();
		Area end = null;
		try {
			TypedQuery<Area> query = em.createNamedQuery("AREA", Area.class);
			query.setParameter("area", endereco);
			if(!query.getResultList().isEmpty()){
				end = query.getSingleResult();
			}
		} finally {
			this.finalizar();
		}
		return end;
	}
	
}
