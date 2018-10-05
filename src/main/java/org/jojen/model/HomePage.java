package org.jojen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@ToString
public class HomePage {
    private String companyName;
    private String headline;
    private String subHeadline;

    @DBRef
    private Image logo;
    @DBRef
    private List<Product> products;
}
