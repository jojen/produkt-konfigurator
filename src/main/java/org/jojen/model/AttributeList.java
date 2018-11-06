package org.jojen.model;

import com.github.reinert.jjschema.Attributes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Attributes(title = " ")
public class AttributeList {
    @Attributes(required = true, title = "Attribute")
    private List<Attribute> attributes;
}
