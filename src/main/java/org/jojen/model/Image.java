package org.jojen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.MediaType;


@Document
@Getter
@Setter
@ToString
public class Image {
    @Id
    private String id;
    private String filename;
    private String extention;
    private String mimetype;

    public MediaType getMediaType(boolean thumb) {
        if (thumb) {
            return new MediaType("image", "png");
        }
        if (getMimetype() != null && getMimetype().contains("/")) {
            return new MediaType(getMimetype().split("/")[0], getMimetype().split("/")[1]);
        }
        return null;
    }

    public String getLink() {
        return "/media/" + id + "/" + filename + "." + extention;
    }

    public String getLinkS() {
        return "/media/thumb/" + id + "/s/" + filename + ".png";
    }

    public String getLinkM() {
        return "/media/thumb/" + id + "/m/" + filename + ".png";
    }

    public String getLinkL() {
        return "/media/thumb/" + id + "/l/" + filename + ".png";
    }

}
