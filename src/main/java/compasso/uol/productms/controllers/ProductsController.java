package compasso.uol.productms.controllers;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import compasso.uol.productms.entities.Product;
import compasso.uol.productms.services.ProductService;

@RestController
@Validated
@RequestMapping("products")
public class ProductsController {

	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<?> getProducts() {
		return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> newProduct(@RequestBody @Valid Product product) {
		return new ResponseEntity<>(productService.newProduct(product), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable("id") Long id) {

		if (productService.getProduct(id) == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(productService.getProduct(id));
	}

	@GetMapping("/search")
	public ResponseEntity<?> getFoos(@RequestParam(required = false) String q,
			@RequestParam(required = false) Double min_price, @RequestParam(required = false) Double max_price) {

		return new ResponseEntity<>(productService.search(q, min_price, max_price), HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(
			@PathVariable("id") @Valid @Positive(message = "size should be positive number") Long id) {

		if (!productService.delete(id)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid Product product, @PathVariable Long id) {

		Product updatedProduct = productService.update(id, product);

		if (updatedProduct == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedProduct);

	}

}