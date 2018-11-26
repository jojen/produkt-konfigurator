package org.jojen.model;

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
public class Product {
    @Id
    private String id;
    private String title;
    private String titleUrlFriendly;
    private String description;

    @DBRef
    private Image image;

    private SizeAttribute width;

    private SizeAttribute height;

    private SizeAttribute length;

    private List<Property> properties;

    private List<Accessory> accessories;

    private Double woodbaseprice;

    private Double fixcosts;

    public String getId() {
        return id;
    }

    public SizeAttribute getWidth() {
        if (width == null) {
            width = new SizeAttribute();
        }
        return width;
    }

    public SizeAttribute getHight() {
        if (height == null) {
            height = new SizeAttribute();
        }
        return height;
    }

    public SizeAttribute getLength() {
        if (length == null) {
            length = new SizeAttribute();
        }
        return length;
    }
}
