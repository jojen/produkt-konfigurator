package org.jojen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
@ToString
public class SizeAttribute {
    Double xs;
    Double s;
    Double m;
    Double l;
    Double xl;
    Double xxl;

    String xsDescription;
    String sDescription;
    String mDescription;
    String lDescription;
    String xlDescription;
    String xxlDescription;

    boolean allowCustomSize;
}
