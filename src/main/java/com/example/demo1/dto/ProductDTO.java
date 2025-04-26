package com.example.demo1.dto;

import com.example.demo1.Config.Nameable;
import com.example.demo1.Config.UniqueNameList;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Nameable {


    private Long id;

    @NotBlank(message = "Name is Required")
    @UniqueNameList
    private String name;

    @NotNull
    @Positive(message = "Price should be positive")
    private Double price;

    @Positive
    @NotNull (message = "Quantity should be positive")
    private Integer quantity;
}
