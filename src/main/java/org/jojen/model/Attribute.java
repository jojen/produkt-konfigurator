package org.jojen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;


@Document
@Getter
@Setter
@ToString
public class Attribute {
    @Id
    private ObjectId id;
    private String title;
    private String type;
    private List<String> values;
    private Double price;
}
