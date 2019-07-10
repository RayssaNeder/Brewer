package com.algaworks.brewer.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.repository.filter.CervejaFilter;

public class CervejasImpl implements CervejasQueries{
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Cerveja> filtrar(CervejaFilter cervejaFilter) {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		
		if(cervejaFilter != null) {
			if(!StringUtils.isEmpty(cervejaFilter.getSku())) {
				criteria.add(Restrictions.eq("sku", cervejaFilter.getSku()));
			}
			
			if(!StringUtils.isEmpty(cervejaFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", cervejaFilter.getNome(), MatchMode.ANYWHERE));
			}
			
			if(isEstiloPresent(cervejaFilter)) {
				criteria.add(Restrictions.eq("estilo", cervejaFilter.getEstilo()));
			}
			
			if(cervejaFilter.getSabor() != null) {
				criteria.add(Restrictions.eq("sabor", cervejaFilter.getSabor()));
			}
			
			if(cervejaFilter.getOrigem() != null) {
				criteria.add(Restrictions.eq("origem", cervejaFilter.getOrigem()));
			}
			
			if(cervejaFilter.getValorDe() != null) {
				criteria.add(Restrictions.ge("valor", cervejaFilter.getValorDe()));
			}
			
			if(cervejaFilter.getValorDe() != null) {
				criteria.add(Restrictions.le("valor", cervejaFilter.getValorAte()));
			}
		}
		
		return criteria.list();
	}

	private boolean isEstiloPresent(CervejaFilter cervejaFilter) {
		return cervejaFilter.getEstilo() != null && cervejaFilter.getEstilo().getCodigo() != null;
	}

	

}
