package com.craftdemo.inventorymanager.controller;

import ch.qos.logback.classic.Logger;
import com.craftdemo.inventorymanager.dto.ProductFilterCriteria;
import com.craftdemo.inventorymanager.dto.ProductInfoDTO;
import com.craftdemo.inventorymanager.dto.ResponseDTO;
import com.craftdemo.inventorymanager.service.ProductService;
import com.craftdemo.inventorymanager.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/products")
public class ProductController {
    Logger logger = (Logger) LoggerFactory.getLogger(ProductServiceImpl.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductInfoDTO> getFilteredProducts(@Valid @ModelAttribute ProductFilterCriteria criteria,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size,
                                                    @RequestParam(defaultValue = "shelf.isPrime") String orderBy,
                                                     @RequestParam(defaultValue = "dsc") String sortOrder){
        return productService.getFilteredData(criteria, orderBy, sortOrder, page, size);

    }
    @PostMapping
    public ResponseEntity<ResponseDTO> addProduct(@Valid @RequestBody ProductInfoDTO productInfoDTO ){
        productService.addProduct(productInfoDTO);
        logger.info("Product added successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body( new ResponseDTO("Product added successfully",HttpStatus.CREATED.value()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @PathVariable Long id, @Valid @RequestBody ProductInfoDTO productInfoDTO) {
        productService.updateProduct(id, productInfoDTO);
        return ResponseEntity.status(HttpStatus.OK).body( new ResponseDTO("Product updated successfully",HttpStatus.OK.value()));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct(@Valid @PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO("Product deleted successfully",HttpStatus.OK.value()));
    }

}
