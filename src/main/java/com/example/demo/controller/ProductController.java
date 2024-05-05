package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Table;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@Tag(name="Product", description = "product controller")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value = "/product", produces = "application/json")
    @Operation(
            summary = "Fetch all data product",
            description = "desc"
    )
    public Iterable<Product> getProduct() {
        return this.productRepository.findAll();
    }

    @PostMapping(value = "/product", consumes = "application/json", produces = "application/json")
    public Product createProduct(@RequestBody Product newProduct) {
        return this.productRepository.save(newProduct);
    }

    @PutMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        if (this.productRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("product not found");
        }

        updatedProduct.setId(id);
        this.productRepository.save(updatedProduct);
        return ResponseEntity.ok("success");
    }
    @DeleteMapping(value = "/product/{id}", produces = "application/json")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (this.productRepository.findById(id).isEmpty()){
            return ResponseEntity.status(400).body("product not found");
        }

        this.productRepository.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }

    @PatchMapping(value = "/product/price", produces = "application/json")
    public String updateProductPrice() {
        return "price updated";
    }
}
