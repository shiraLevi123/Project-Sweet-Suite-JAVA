package com.example.projectjavaandreact.controller;

import com.example.projectjavaandreact.model.Suite;
import com.example.projectjavaandreact.service.SuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suites")
@CrossOrigin
public class SuiteController {

    @Autowired
    private SuiteRepository suiteRepository;

    // הצגת כל הסוויטות
    @GetMapping
    public List<Suite> getAllSuites() {
        return suiteRepository.findAll();
    }

    // הצגת פרטי סוויטה על פי מזהה
    @GetMapping("/{id}")
    public ResponseEntity<Suite> getSuiteById(@PathVariable Long id) {
        return suiteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // חיפוש סוויטות לפי קריטריונים
    @GetMapping("/search")
    public List<Suite> searchSuites(
            @RequestParam(required = false) Boolean pool,
            @RequestParam(required = false) Boolean jacuzzi,
            @RequestParam(required = false) Boolean wifi,
            @RequestParam(required = false) Boolean seaView,
            @RequestParam(required = false) Boolean parking,
            @RequestParam(required = false) Boolean airConditioning,
            @RequestParam(required = false) Boolean kitchenFacilities,
            @RequestParam(required = false) Boolean tv,
            @RequestParam(required = false) Integer numberBeds) {

        List<Suite> suites = suiteRepository.findAll();

        // מסננים לפי הקריטריונים
        return suites.stream()
                .filter(suite -> pool == null || suite.isPool() == pool)
                .filter(suite -> jacuzzi == null || suite.isJacuzzi() == jacuzzi)
                .filter(suite -> wifi == null || suite.isWifi() == wifi)
                .filter(suite -> seaView == null || suite.isSeaView() == seaView)
                .filter(suite -> parking == null || suite.isParking() == parking)
                .filter(suite -> airConditioning == null || suite.isAirConditioning() == airConditioning)
                .filter(suite -> kitchenFacilities == null || suite.isKitchenFacilities() == kitchenFacilities)
                .filter(suite -> tv == null || suite.isTv() == tv)
                .filter(suite -> numberBeds == null || suite.getNumberBeds() == numberBeds)
                .toList();
    }


    // הוספת סוויטה
    @PostMapping
    public Suite createSuite(@RequestBody Suite suite) {
        return suiteRepository.save(suite);
    }

    // מחיקת סוויטה
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteSuite(@PathVariable Long id) {
        return suiteRepository.findById(id)
                .map(suite -> {
                    suiteRepository.delete(suite);
                    return ResponseEntity.ok().build();
                })
                .orElseGet(() -> {
                    System.out.println("Suite not found for ID: " + id);
                    return ResponseEntity.notFound().build();
                });
    }

    // עדכון סוויטה
    @PutMapping("/{id}")
    public ResponseEntity<Suite> updateSuite(@PathVariable Long id, @RequestBody Suite suiteDetails) {
        return suiteRepository.findById(id)
                .map(suite -> {
                    suite.setCity(suiteDetails.getCity());
                    suite.setAddress(suiteDetails.getAddress());
                    suite.setPool(suiteDetails.isPool());
                    suite.setJacuzzi(suiteDetails.isJacuzzi());
                    suite.setWifi(suiteDetails.isWifi());
                    suite.setSeaView(suiteDetails.isSeaView());
                    suite.setParking(suiteDetails.isParking());
                    suite.setAirConditioning(suiteDetails.isAirConditioning());
                    suite.setKitchenFacilities(suiteDetails.isKitchenFacilities());
                    suite.setTv(suiteDetails.isTv());
                    suite.setRating(suiteDetails.getRating());
                    suite.setPricePerNight(suiteDetails.getPricePerNight());
                    suite.setNumberBeds(suiteDetails.getNumberBeds());

                    // עדכון יתר הפרמטרים
                    Suite updatedSuite = suiteRepository.save(suite);
                    return ResponseEntity.ok(updatedSuite);
                })
                .orElse(ResponseEntity.notFound().build());
    }

}