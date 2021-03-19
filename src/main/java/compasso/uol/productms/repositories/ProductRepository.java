/**
 * 
 */
package compasso.uol.productms.repositories;

import org.springframework.data.repository.CrudRepository;

import compasso.uol.productms.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Long>, ProductRepositoryCustom {

	 
	
}
