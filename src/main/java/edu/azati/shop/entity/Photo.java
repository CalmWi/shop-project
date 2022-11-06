package edu.azati.shop.entity;

import lombok.*;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Document(collection = "photo")
public class Photo {
    @Id
    private String id;
    private String title;
    private Binary image;

    public Photo(String title){
        this.title = title;
    }
}
