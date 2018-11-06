package org.jojen.model;

import com.github.reinert.jjschema.Attributes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.List;


@Getter
@Setter
@ToString
@Attributes(title = "Attribut")
public class Attribute {
    @Attributes(required = true, title = "Titel")
    private String title;
    @Attributes(required = true, title = "Typ", enums = {"Zahl", "Text"})
    private String type;
    @Attributes(required = true, title = "Werte")
    private List<String> values;
    @Attributes(required = true, title = "Preis")
    private Double price;
}
