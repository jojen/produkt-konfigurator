package org.jojen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
