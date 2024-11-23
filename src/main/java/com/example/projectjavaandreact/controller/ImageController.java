package com.example.projectjavaandreact.controller;

import com.example.projectjavaandreact.dto.ImageDTO;
import com.example.projectjavaandreact.model.Images;
import com.example.projectjavaandreact.service.ImageRepository;
import com.example.projectjavaandreact.service.MapstructMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/images")
@CrossOrigin
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private MapstructMapper mapstructMapper;

    // קבלת כל התמונות
    @GetMapping
    public List<ImageDTO> getAllImages() {
        return imageRepository.findAll().stream()
                .map(mapstructMapper::imageToImageDTO) // שימוש במאפפר להמרה ל-DTO
                .collect(Collectors.toList());
    }

    // יצירת תמונה חדשה
    @PostMapping
    public ImageDTO createImage(@RequestBody ImageDTO imageDTO) {
        Images imageEntity = mapstructMapper.imageDTOToImage(imageDTO); // המרת DTO ל-Entity
        Images savedImage = imageRepository.save(imageEntity); // שמירת ה-Entity ב-DB
        return mapstructMapper.imageToImageDTO(savedImage); // המרת ה-Entity שנשמר ל-DTO
    }

    // קבלת תמונה לפי ID
    @GetMapping("/{id}")
    public ImageDTO getImageById(@PathVariable Long id) {
        Images image = imageRepository.findById(id).orElse(null);
        return image != null ? mapstructMapper.imageToImageDTO(image) : null;
    }

//    // עדכון תמונה
//    @PutMapping("/{id}")
//    public ImageDTO updateImage(@PathVariable Long id, @RequestBody ImageDTO imageDTO) {
//        Images imageEntity = mapstructMapper.imageDTOToImage(imageDTO); // המרת DTO ל-Entity
//        imageEntity.setId(id); // הגדרת ה-ID
//        Images updatedImage = imageRepository.save(imageEntity); // שמירת ה-Entity המעודכן
//        return mapstructMapper.imageToImageDTO(updatedImage); // המרת ה-Entity ל-DTO
//    }
//
//    // מחיקת תמונה
//    @DeleteMapping("/{id}")
//    public void deleteImage(@PathVariable Long id) {
//        imageRepository.deleteById(id);
//    }
}
