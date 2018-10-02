package com.schmalz.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
public class Image {
    @Id
    private String id;
    private String filename;
    private String mimetype;
}
