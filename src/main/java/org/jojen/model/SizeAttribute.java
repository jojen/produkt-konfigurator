package org.jojen.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getTickLabels() {
        List<String> ret = new ArrayList<>();
        if (xs != null) {
            ret.add("XS");
        }
        if (s != null) {
            ret.add("S");
        }
        if (m != null) {
            ret.add("M");
        }
        if (l != null) {
            ret.add("L");
        }
        if (xl != null) {
            ret.add("XL");
        }
        if (xxl != null) {
            ret.add("XXL");
        }

        return ret;
    }

    public String getTickLabelsAsString() throws JsonProcessingException {
        return  new ObjectMapper().writeValueAsString(getTickLabels());
    }

    public List<Integer> getTicks() {
        List<Integer> ret = new ArrayList<>();
        Integer i = 0;
        i = addSize(xs, i, ret);
        i = addSize(s, i, ret);
        i = addSize(m, i, ret);
        i = addSize(l, i, ret);
        i = addSize(xl, i, ret);
        addSize(xxl, i, ret);
        return ret;
    }

    public Integer getSliderMax() {
        return getTicks().size();
    }

    public Integer getSliderValue() {
        int i = 0;
        for (String label : getTickLabels()) {
            i++;
            if (label.equals("M")) {
                return i;
            }
        }
        return 1;
    }


    private Integer addSize(Double size, Integer i, List<Integer> ret) {
        if (size != null) {
            i = i + 1;
            ret.add(i);
        }
        return i;
    }
}
