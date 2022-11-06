package edu.azati.shop.services;

import edu.azati.shop.entity.Photo;
import edu.azati.shop.repository.PhotoRepo;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PhotoService {
    @Autowired
    PhotoRepo photoRepo;

    public void addPhoto(String title, MultipartFile file) throws IOException {
        Photo photo = new Photo(title);
        photo.setImage(new Binary(BsonBinarySubType.BINARY, file.getBytes()));
        photo = photoRepo.insert(photo);
    }

    public Photo getPhoto(String title) {
        return photoRepo.findPhotoByTitle(title);
    }
}
