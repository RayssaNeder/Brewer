package com.algaworks.brewer.repository.helper.cliente;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cliente;
import com.algaworks.brewer.repository.filter.ClienteFilter;
import com.algaworks.brewer.repository.paginacao.PaginacaoUtil;

public class ClientesImpl implements ClientesQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Override
	@Transactional(readOnly = true)
	public Page<Cliente> filtrar(ClienteFilter clienteFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(clienteFilter, criteria);
		criteria.createAlias("endereco.cidade", "c", JoinType.LEFT_OUTER_JOIN);
		criteria.createCriteria("c.estado", "e", JoinType.LEFT_OUTER_JOIN);
		return new PageImpl<>(criteria.list(), pageable, total(clienteFilter));
	}

	private Long total(ClienteFilter clienteFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Cliente.class);
		adicionarFiltro(clienteFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionarFiltro(ClienteFilter clienteFilter, Criteria criteria) {
		if(clienteFilter != null) {
			if(!StringUtils.isEmpty(clienteFilter.getCpfOuCnpj())) {
				criteria.add(Restrictions.eq("cpfOuCnpj", clienteFilter.getCpfOuCnpj()));
			}
			
			if(!StringUtils.isEmpty(clienteFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", clienteFilter.getNome(), MatchMode.ANYWHERE));
			}
		}
		
	}

}
