package br.mbsistemas.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import br.mbsistemas.model.Endereco;

public class EnderecoDAO extends HibernateDAO<Endereco>{
	
	public Endereco buscarEndereco(String endereco) {
		this.conectar();
		Endereco end = null;
		try {
			TypedQuery<Endereco> query = em.createNamedQuery("ENDERECO", Endereco.class);
			query.setParameter("endereco", endereco);
			if(!query.getResultList().isEmpty()){
				end = query.getSingleResult();
			}
		} finally {
			this.finalizar();
		}
		return end;
	}

}
