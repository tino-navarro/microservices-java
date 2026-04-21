package br.edu.atitus.productservice.controllers;

import br.edu.atitus.productservice.dtos.ProductDTO;
import br.edu.atitus.productservice.entities.ProductEntity;
import br.edu.atitus.productservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository){
        this.repository = repository;
    }

    @Value("${server.port}")
    private String port;

    @GetMapping("/{idproduct}")

    public ResponseEntity<ProductDTO> getId(
            @PathVariable Long idproduct,
            @RequestParam String targetCurrency){

        targetCurrency = targetCurrency.toUpperCase();

        ProductEntity product = repository
                .findById(idproduct)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        ProductDTO dto = new ProductDTO(
                product.getId(),
                product.getDescription(),
                product.getBrand(),
                product.getModel(),
                product.getPrice(),
                product.getCurrency(),
                product.getStock(),
                "Product-service running on Port: " + port,
                null,
                targetCurrency
        );


    return ResponseEntity.ok(dto);
    }
}
