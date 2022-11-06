package edu.azati.shop;

import edu.azati.shop.entity.Photo;
import edu.azati.shop.services.PhotoService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PhotoServiceTest {
    @Mock
    PhotoService photoService;

    @Test
    public void photoServiceGetPhotoByTitleTest() {
        Photo photo = new Photo("header");
        when(photoService.getPhoto("header")).thenReturn(photo);
        Assert.assertEquals("header", photo.getTitle());
    }
}
