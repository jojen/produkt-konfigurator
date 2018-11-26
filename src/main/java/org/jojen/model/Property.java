package org.jojen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Property {

    private String title;
    private List<Value> value;
}
