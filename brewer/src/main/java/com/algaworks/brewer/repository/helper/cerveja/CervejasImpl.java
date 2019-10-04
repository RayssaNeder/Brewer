package com.algaworks.brewer.repository.helper.cerveja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
	public Page<Cerveja> filtrar(CervejaFilter cervejaFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistroPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistroPorPagina);
		
		Sort sort = pageable.getSort();
		System.out.println(">> sort " + sort);
		if(sort != null) {
			Sort.Order order = sort.iterator().next();
			String field = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(field) : Order.desc(field));
		}
		
		adicionarFiltro(cervejaFilter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(cervejaFilter));
	}

	private Long total(CervejaFilter cervejaFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cerveja.class);
		adicionarFiltro(cervejaFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	private void adicionarFiltro(CervejaFilter cervejaFilter, Criteria criteria) {		
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
	}


	private boolean isEstiloPresent(CervejaFilter cervejaFilter) {
		return cervejaFilter.getEstilo() != null && cervejaFilter.getEstilo().getCodigo() != null;
	}

	

}
