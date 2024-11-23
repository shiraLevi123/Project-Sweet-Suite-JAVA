package com.example.projectjavaandreact.controller;

import com.example.projectjavaandreact.model.Review;
import com.example.projectjavaandreact.service.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@CrossOrigin
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    // יצירת ביקורת חדשה
    @PostMapping
    public Review createReview(@RequestBody Review review) {
        return reviewRepository.save(review); // שמירת הביקורת
    }

    // קבלת כל הביקורות
    @GetMapping
    public List<Review> getAllReviews() {
        return reviewRepository.findAll(); // קבלת כל הביקורות
    }

    // קבלת ביקורת לפי ID
    @GetMapping("/{id}")
    public Review getReviewById(@PathVariable Long id) {
        return reviewRepository.findById(id).orElse(null); // קבלת ביקורת לפי ID
    }

    // עדכון ביקורת
    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review review) {
        // ניתן לבדוק אם הביקורת קיימת לפני העדכון
        review.setId(id); // הגדרת ה-ID של הביקורת המעודכנת
        return reviewRepository.save(review); // שמירת הביקורת המעודכנת
    }

    // מחיקת ביקורת
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewRepository.deleteById(id); // מחיקת ביקורת לפי ID
    }
}