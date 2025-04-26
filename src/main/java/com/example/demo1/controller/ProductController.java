package com.example.demo1.controller;

import com.example.demo1.Handlers.ResponseWrapper;
import com.example.demo1.dto.ProductDTO;
import com.example.demo1.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "")
    public ResponseEntity<ResponseWrapper<List<ProductDTO>>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        ResponseWrapper<List<ProductDTO>> response = new ResponseWrapper<>(LocalDateTime.now(),
                HttpStatus.ACCEPTED.value(),
                "Products retrieved Succesfullly",
                products,
                false);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/add")
    public  ResponseEntity<ResponseWrapper<ProductDTO>> addProduct(@RequestBody @Valid ProductDTO productDTO) {
        ProductDTO addedProduct = productService.addProduct(productDTO);
        if(addedProduct != null){
            ResponseWrapper<ProductDTO> response = new ResponseWrapper<>(
                    LocalDateTime.now(),
                    HttpStatus.CREATED.value(),
                    "Product added Successfully",
                    addedProduct,
                    false
            );
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new ResponseWrapper<>(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Product not addded",
                null,
                true
        ), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path = "/{id}")
    public  ResponseEntity<ResponseWrapper<ProductDTO>> deleteProduct(@PathVariable Long id){
        ProductDTO deletedProduct = productService.deleteProduct(id);
        ResponseWrapper<ProductDTO> response = new ResponseWrapper<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Product Deleted Successfully",
                deletedProduct,
                false
        );
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ResponseWrapper<ProductDTO>> updateProdcut(@PathVariable  Long id ,@RequestBody ProductDTO productDTO){
        ProductDTO updatedProduct = productService.updateProduct(id,productDTO);
        ResponseWrapper<ProductDTO> response  = new ResponseWrapper<>(
                LocalDateTime.now(),
                HttpStatus.CREATED.value(),
                "Product Updated Successfully",
                updatedProduct,
                false
        );
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PatchMapping(path ="/{id}")
    public ResponseEntity<ResponseWrapper<ProductDTO>> editProduct(@PathVariable Long id, @RequestBody Map<String, Object> updates){
        ProductDTO edittedProduct = productService.editProduct(id,updates);
        if(edittedProduct == null){
            ResponseWrapper<ProductDTO> product = new ResponseWrapper<>(
                    LocalDateTime.now(),
                    HttpStatus.CREATED.value(),
                    "Product Not found",
                    null,
                    false
            );
            return new ResponseEntity<>(product,HttpStatus.NOT_FOUND);
        }
        else{
            ResponseWrapper<ProductDTO> product = new ResponseWrapper<>(
                    LocalDateTime.now(),
                    HttpStatus.ACCEPTED.value(),
                    "Product Eddited Successfully",
                    edittedProduct,
                    false
            );
            return new ResponseEntity<>(product,HttpStatus.ACCEPTED);
        }
    }
}
