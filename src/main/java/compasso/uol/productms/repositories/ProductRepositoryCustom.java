/**
 * 
 */
package compasso.uol.productms.repositories;

import java.util.List;

import compasso.uol.productms.entities.Product;

public interface ProductRepositoryCustom {

	  List<Product> search(String q, Double min_price, Double max_price);
}
