package com.algaworks.brewer.repository.helper.estilo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Estilo;
import com.algaworks.brewer.repository.filter.EstiloFilter;

public class EstilosImpl implements EstilosQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Estilo> filtrar(EstiloFilter estiloFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistroPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistroPorPagina);
		
		Sort sort = pageable.getSort();
		if(sort != null) {
			Sort.Order order = sort.iterator().next();
			String field = order.getProperty();
			criteria.addOrder(order.isAscending() ? Order.asc(field) : Order.desc(field));
		}
		
		adicionarFiltro(estiloFilter, criteria);
		
		return new PageImpl<>(criteria.list(), pageable, total(estiloFilter));
	}
	

	
	private Long total(EstiloFilter estiloFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Estilo.class);
		adicionarFiltro(estiloFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}



	private void adicionarFiltro(EstiloFilter estiloFilter, Criteria criteria) {
		if(estiloFilter != null) {
			if(!StringUtils.isEmpty(estiloFilter.getNome())){
				criteria.add(Restrictions.ilike("nome", estiloFilter.getNome(), MatchMode.ANYWHERE));
			}
		}
	}

}
