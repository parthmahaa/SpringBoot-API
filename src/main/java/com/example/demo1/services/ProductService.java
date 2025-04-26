package com.example.demo1.services;

import com.example.demo1.dto.ProductDTO;
import com.example.demo1.entities.ProductEntity;
import com.example.demo1.repo.ProductRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepo productRepo, ModelMapper modelMapper) {
        this.productRepo = productRepo;
        this.modelMapper = modelMapper;
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductEntity> productEntity = productRepo.findAll();
        return productEntity.stream()
                .map(entity -> modelMapper.map(entity, ProductDTO.class))
                .toList();
    }

    public ProductDTO addProduct(ProductDTO productDTO) {
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        try{
            ProductEntity savedProduct = productRepo.save(productEntity);
            return modelMapper.map(savedProduct,ProductDTO.class);
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to add product:" + e.getMessage());
        }
    }

    public ProductDTO deleteProduct(Long id){
          ProductEntity productEntity =productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product doesn't exist"));
        try{
             productRepo.delete(productEntity);
            return modelMapper.map(productEntity, ProductDTO.class);
          }catch (RuntimeException e){
              throw new RuntimeException("Failed to Delete Product:" + e.getMessage());
          }
    }

    public ProductDTO updateProduct(Long id , ProductDTO productDTO){
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        productEntity.setId(id);
        try{
            ProductEntity savedProduct = productRepo.save(productEntity);
            return modelMapper.map(savedProduct, ProductDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to Update" + e.getMessage());
        }
    }

    public ProductDTO editProduct (Long id, Map<String, Object>updates){
        boolean exists = productRepo.existsById(id);
        if(exists){
            try{
                ProductEntity productToEdit = productRepo.findById(id).orElseThrow(()-> new RuntimeException("Product Not Found"));
                updates.forEach((field, value) -> {
                    Field fieldToUpdate = ReflectionUtils.findField(ProductEntity.class, field);
                    if (fieldToUpdate != null) {
                        fieldToUpdate.setAccessible(true);
                        ReflectionUtils.setField(fieldToUpdate, productToEdit, value);
                    }
                });

                // Save the updated entity to persist changes
                ProductEntity updatedProduct = productRepo.save(productToEdit);
                return modelMapper.map(updatedProduct, ProductDTO.class);
            }catch (Exception e){
                throw new RuntimeException("failed to update employee"+ e.getMessage());
            }
        }
        else return null;
    }
}

