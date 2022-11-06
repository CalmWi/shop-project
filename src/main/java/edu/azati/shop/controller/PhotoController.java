package edu.azati.shop.controller;

import edu.azati.shop.entity.Photo;
import edu.azati.shop.entity.User;
import edu.azati.shop.services.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

@Controller
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @GetMapping("/photo")
    public String showForm(Photo photo) {
        return "uploadPhoto";
    }

    @PostMapping("/photos/add")
    public String addPhoto(@RequestParam("title") String title,
                           @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        photoService.addPhoto(title, image);
        return "redirect:/photo";
    }

/*    @GetMapping("/photos/{title}")
    public String getPhotoByTitle(@PathVariable String title, Model model) {
        Photo photo = photoService.getPhoto(title);
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));
        return "photos";
    }*/

    @GetMapping("/image/display/{title}")
    @ResponseBody
    public void showImage(@PathVariable("title") String title, HttpServletResponse response)
            throws ServletException, IOException {
        Photo photo = photoService.getPhoto("header");
        response.setContentType("image/jpg");
        response.getOutputStream().write(photo.getImage().getData());
        response.getOutputStream().close();
    }
}
