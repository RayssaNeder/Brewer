package com.algaworks.brewer.repository.helper.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.Grupo;
import com.algaworks.brewer.model.Usuario;
import com.algaworks.brewer.model.UsuarioGrupo;
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
	
	
	/*
	 *  select * from usuario u
      inner join usuario_grupo ug on u.codigo = ug.codigo_usuario
      inner join grupo g on ug.codigo_grupo = g.codigo
      where(
		   u.codigo in (select codigo_usuario from usuario_grupo where codigo_grupo = 1)
       and u.codigo in (select codigo_usuario from usuario_grupo where codigo_grupo = 2))
	 */

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		paginacaoUtil.preparar(criteria, pageable);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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
				criteria.add(Restrictions.ilike("nome", usuarioFilter.getNome(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(usuarioFilter.getEmail())) {
				criteria.add(Restrictions.ilike("email", usuarioFilter.getEmail(), MatchMode.START));
			}
			
			criteria.createAlias("grupos", "g", JoinType.LEFT_OUTER_JOIN);
			if(usuarioFilter.getGrupos() != null && !usuarioFilter.getGrupos().isEmpty()) {
				List<Criterion> subqueries = new ArrayList<>();
				for(Long codigoGrupo : usuarioFilter.getGrupos().stream().mapToLong(Grupo::getCodigo).toArray()) {
					DetachedCriteria dc = DetachedCriteria.forClass(UsuarioGrupo.class);
					dc.add(Restrictions.eq("id.grupo.codigo", codigoGrupo));
					dc.setProjection(Projections.property("id.usuario"));
					
					subqueries.add(Subqueries.propertyIn("codigo", dc));
				}
				
				Criterion[] criterions = new	Criterion[subqueries.size()];
				criteria.add(Restrictions.and(subqueries.toArray(criterions)));
						}
		}
		
	}
	

}
