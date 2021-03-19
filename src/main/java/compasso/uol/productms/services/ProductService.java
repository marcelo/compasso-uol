package compasso.uol.productms.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import compasso.uol.productms.entities.Product;
import compasso.uol.productms.repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public Product update(Long id, Product product) {

		Optional<Product> currentProduct = productRepository.findById(id);

		if (!currentProduct.isPresent()) {
			return null;
		}

		currentProduct.get().setDescription(product.getDescription());
		currentProduct.get().setName(product.getName());
		currentProduct.get().setPrice(product.getPrice());

		return productRepository.save(currentProduct.get());

	}

	public boolean delete(Long id) {

		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
			return false;
		}

		productRepository.delete(product.get());
		return true;
	}

	public Iterable<Product> getProducts() {

		return productRepository.findAll();
	}

	public Product getProduct(Long id) {

		Optional<Product> product = productRepository.findById(id);

		if (!product.isPresent()) {
			return null;
		}

		return product.get();
	}

	public Product newProduct(Product product) {

		return productRepository.save(product);

	}

	public Iterable<Product> search(String q, Double min_price, Double max_price) {

		return productRepository.search(q, min_price, max_price);

	}
}
