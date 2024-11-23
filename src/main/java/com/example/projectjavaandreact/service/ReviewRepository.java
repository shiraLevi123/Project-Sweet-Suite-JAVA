package com.example.projectjavaandreact.service;

import com.example.projectjavaandreact.model.Review;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}