package org.jojen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Getter
@Setter
@ToString
public class Product {
    @Id
    private String id;
    private String title;
    private String titleUrlFriendly;
    private String description;

    @DBRef
    private List<Image> image;

    @DBRef
    private List<Attribute> step1;

    @DBRef
    private List<Attribute> step2;

    @DBRef
    private List<Attribute> step3;

    @DBRef
    private List<Attribute> step4;

    @DBRef
    private List<Attribute> step5;

    private Double price;
}
