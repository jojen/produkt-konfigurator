package org.jojen.model;

import com.github.reinert.jjschema.Attributes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Attributes(title = "AttributeList", description = "AttributeList for product")
public class AttributeList {
    @Attributes(required = true, title = "Attributes")
    private List<Attribute> attributes;
}
