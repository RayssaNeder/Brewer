package com.algaworks.brewer.repository.helper.usuario;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.repository.filter.CervejaFilter;
import com.algaworks.brewer.repository.filter.CidadeFilter;
import com.algaworks.brewer.repository.filter.UsuarioFilter;
import com.algaworks.brewer.repository.paginacao.PaginacaoUtil;

public class UsuariosImpl implements UsuariosQueries {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private PaginacaoUtil paginacaoUtil;

	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		return manager.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}

	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery("select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
		.setParameter("usuario", usuario)
		.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(usuarioFilter, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(usuarioFilter));
	}
	
	private Long total(UsuarioFilter usuarioFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		adicionarFiltro(usuarioFilter, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	
	private void adicionarFiltro(UsuarioFilter usuarioFilter, Criteria criteria) {
		if(usuarioFilter != null) {
			
			if(!StringUtils.isEmpty(usuarioFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", usuarioFilter.getNome()));
			}
			if(!StringUtils.isEmpty(usuarioFilter.getEmail())) {
				criteria.add(Restrictions.eq("email", usuarioFilter.getEmail()));
			}
		}
		
	}
	

}
