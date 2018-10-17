package org.jojen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.reinert.jjschema.JsonSchemaGenerator;
import com.github.reinert.jjschema.SchemaGeneratorBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.IOException;


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

    private AttributeList attributes;

    private Double width;

    private Double height;

    private Double length;

    private Double price;

    private Double pricelevel;

    public String getAttributeJson() throws JsonProcessingException {
        if (getAttributes() != null && !getAttributes().getAttributes().isEmpty()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(getAttributes());
        }
        return null;
    }

    public void setAttributeJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<AttributeList> typeRef
                = new TypeReference<AttributeList>() {
        };
        setAttributes(mapper.readValue(json, typeRef));
    }

    public JsonNode getAttributeSchema() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        JsonSchemaGenerator v4generator = SchemaGeneratorBuilder.draftV4HyperSchema().build();
        return v4generator.generateSchema(AttributeList.class);
    }
}
