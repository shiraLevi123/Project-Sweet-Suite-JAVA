package com.example.projectjavaandreact.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Images {
    @Id
    private Long id;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "suite_id")
    @JsonBackReference
    private Suite suite;

    // גטרים
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }
}
