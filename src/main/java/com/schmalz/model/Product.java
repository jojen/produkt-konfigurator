package com.schmalz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.Generated;
import java.util.List;


@Document
@Getter
@Setter
@ToString
public class Product {
    @Id
    private ObjectId id;
    private String title;
    @DBRef
    private List<Image> image;
    private String description;
    private List<ProductOption> options;
    private List<Attribute> attributes;
    @DBRef
    private List<ProductOption> excluded;

    private Double price;
}
