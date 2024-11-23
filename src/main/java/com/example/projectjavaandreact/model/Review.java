package com.example.projectjavaandreact.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
    @Id
    private Long id;
    private int rating;
    private String comment;

    @ManyToOne
    private Suite suite;

    @ManyToOne
    private Customer customer;

    public Review() {
    }

    public Review(Long id, int rating, String comment, Suite suite, Customer customer) {
        this.id = id;
        this.rating = rating;
        this.comment = comment;
        this.suite = suite;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Suite getSuite() {
        return suite;
    }

    public void setSuite(Suite suite) {
        this.suite = suite;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
