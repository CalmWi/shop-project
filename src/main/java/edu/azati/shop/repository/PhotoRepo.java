package edu.azati.shop.repository;

import edu.azati.shop.entity.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PhotoRepo extends MongoRepository<Photo, String> {

    Photo findPhotoByTitle(String title);

    Photo findByTitle(String title);
}
