package com.example.projectjavaandreact.service;

import com.example.projectjavaandreact.model.Images; // ודאי שאת מייבאת את המודל הנכון
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Images, Long> {
}