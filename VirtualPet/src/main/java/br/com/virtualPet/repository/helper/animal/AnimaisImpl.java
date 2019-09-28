package br.com.virtualPet.repository.helper.animal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import br.com.virtualPet.modelo.Animal;
import br.com.virtualPet.repository.filter.AnimalFilter;


public class AnimaisImpl implements AnimaisQueries {
	
	@PersistenceContext
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public Page<Animal> filtrar(AnimalFilter animalFilter, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Animal.class);
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistroPorPagina;
		
		criteria.setFirstResult(primeiroRegistro);
		criteria.setMaxResults(totalRegistroPorPagina);
		
		adicionaFiltro(animalFilter, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(animalFilter));
	}

	private Long total(AnimalFilter animalFilter) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Animal.class);
		adicionaFiltro(animalFilter,criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}

	private void adicionaFiltro(AnimalFilter animalFilter, Criteria criteria) {
		if(animalFilter != null) {
			if(!StringUtils.isEmpty(animalFilter.getNome())) {
				criteria.add(Restrictions.ilike("nome", animalFilter.getNome(), MatchMode.ANYWHERE));
			}
		}
		
	}

}
