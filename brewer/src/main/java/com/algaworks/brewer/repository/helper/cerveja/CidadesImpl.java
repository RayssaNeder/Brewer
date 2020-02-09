package com.algaworks.brewer.repository.helper.cerveja;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.algaworks.brewer.model.Cidade;
import com.algaworks.brewer.repository.filter.CidadeFilter;
import com.algaworks.brewer.repository.paginacao.PaginacaoUtil;

public class CidadesImpl implements CidadesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Cidade> filtrar(CidadeFilter cidadeFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(cidadeFilter, criteria);
		criteria.createAlias("estado", "c", JoinType.LEFT_OUTER_JOIN);
		//criteria.createCriteria("c", "e", JoinType.LEFT_OUTER_JOIN);
		return new PageImpl<>(criteria.list(), pageable, total(cidadeFilter));
	}

	private Long total(CidadeFilter cidadeFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cidade.class);
		adicionarFiltro(cidadeFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(CidadeFilter cidadeFilter, Criteria criteria) {
		if(cidadeFilter != null) {
			
			if(!StringUtils.isEmpty(cidadeFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", cidadeFilter.getNome()));
			}
			if(IsEstadoPresent(cidadeFilter)) {
				criteria.add(Restrictions.eq("estado", cidadeFilter.getEstado()));
			}
		}
		
	}

	private boolean IsEstadoPresent(CidadeFilter cidadeFilter) {
		return cidadeFilter.getEstado() != null && cidadeFilter.getEstado().getCodigo() != null; 
	}

}
