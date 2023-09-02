package com.inventorymanager.inventorymanager.controller;

import com.inventorymanager.inventorymanager.dto.ProductFilterCriteria;
import com.inventorymanager.inventorymanager.dto.ProductInfoDTO;
import com.inventorymanager.inventorymanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductInfoDTO> getFilteredProducts(@ModelAttribute ProductFilterCriteria criteria,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size,
                                                    @RequestParam(defaultValue = "shelf.isPrime") String orderBy,
                                                     @RequestParam(defaultValue = "dsc") String sortOrder){
        return productService.getFilteredData( criteria, orderBy,  sortOrder,  page,  size);
    }

    @GetMapping("/categories")
    public List<String> getAllCategories() {
        return productService.getAllCategories();
    }

    @PostMapping
    public ResponseEntity<String> addProduct(@RequestBody ProductInfoDTO productInfoDTO ){
        productService.addProduct(productInfoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody ProductInfoDTO productInfoDTO) {
        productService.updateProduct(id, productInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Product updated successfully");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

}
