package com.algaworks.brewer.repository.helper.venda;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Grupo;
import com.algaworks.brewer.model.TipoPessoa;
import com.algaworks.brewer.model.UsuarioGrupo;
import com.algaworks.brewer.model.Venda;
import com.algaworks.brewer.repository.filter.CervejaFilter;
import com.algaworks.brewer.repository.filter.UsuarioFilter;
import com.algaworks.brewer.repository.filter.VendaFilter;
import com.algaworks.brewer.repository.paginacao.PaginacaoUtil;

public class VendasImpl implements VendasQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Venda> filtrar(VendaFilter vendaFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(vendaFilter, criteria);
		return new  PageImpl<>(criteria.list(), pageable, total(vendaFilter));
	}
	
	private Long total(VendaFilter vendaFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Venda.class);
		adicionarFiltro(vendaFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	
	private void adicionarFiltro(VendaFilter vendaFilter, Criteria criteria) {
		if(vendaFilter != null) {
			
			criteria.createAlias("cliente", "c");
			
			if(!StringUtils.isEmpty(vendaFilter.getNomeCliente())) {
				criteria.add(Restrictions.ilike("c.nome", vendaFilter.getNomeCliente(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(vendaFilter.getCpfCnpj())) {
				criteria.add(Restrictions.eq("c.cpfOuCnpj", TipoPessoa.removerFormatacao(vendaFilter.getCpfCnpj())));
			}
			if(vendaFilter.getDtCriacaoDe() != null) {
				criteria.add(Restrictions.ge("dtCriacaoDe", vendaFilter.getDtCriacaoDe()));
			}
			
			if(vendaFilter.getDtCriacaoAte() != null) {
				criteria.add(Restrictions.le("dtCriacaoAte", vendaFilter.getDtCriacaoAte()));
			}
			
			if (vendaFilter.getStatus() != null) {
				criteria.add(Restrictions.eq("status", vendaFilter.getStatus()));
			}
			
			if(!StringUtils.isEmpty(vendaFilter.getCodigo()) ) {
				criteria.add(Restrictions.eq("codigo", vendaFilter.getCodigo()));
			}
			
			if(vendaFilter.getVlCriacaoDe() != null ) {
				criteria.add(Restrictions.ge("valorTotal", vendaFilter.getVlCriacaoDe()));
			}
			
			if(vendaFilter.getVlCriacaoAte() != null ) {
				criteria.add(Restrictions.le("valorTotal", vendaFilter.getVlCriacaoAte()));
			}
			
		}
		
	}

}
