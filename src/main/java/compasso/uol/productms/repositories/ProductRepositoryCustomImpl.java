/**
 * 
 */
package compasso.uol.productms.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import compasso.uol.productms.entities.Product;

public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Product> search(String q, Double min_price, Double max_price) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = cb.createQuery(Product.class);
		Root<Product> product = query.from(Product.class);

		List<Predicate> predicates = new ArrayList<>();
		Predicate predicateName = null;
		Predicate predicateDescription = null;
		Predicate predicateForQ = null;
		Predicate predicateMinPrice = null;
		Predicate predicateMaxPrice = null;

		if (q != null) {

			predicateName = cb.equal(product.get("name"), q);
			predicateDescription = cb.equal(product.get("description"), q);
			predicateForQ = cb.or(predicateName, predicateDescription);
			predicates.add(predicateForQ);
		}

		if (min_price != null) {
			predicateMinPrice = cb.ge(product.get("price"), min_price);
			predicates.add(predicateMinPrice);
		}

		if (max_price != null) {

			predicateMaxPrice = cb.le(product.get("price"), max_price);
			predicates.add(predicateMaxPrice);
		}

		query.where(predicates.toArray(new Predicate[predicates.size()]));

		return entityManager.createQuery(query).getResultList();

	}
}
