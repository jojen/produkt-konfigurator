package com.schmalz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@ToString
public class Customer {
    @Id
    private String email;
    private String firstName;
    private String lastName;

    @DBRef
    private Product currentProduct;
    @DBRef
    private List<ProductOption> currentProductOptions;
}
