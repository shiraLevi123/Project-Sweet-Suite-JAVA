package com.example.projectjavaandreact.service;

import com.example.projectjavaandreact.model.Suite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SuiteRepository extends JpaRepository<Suite, Long> {

    List<Suite> findByPool(Boolean pool);

    List<Suite> findByJacuzzi(Boolean jacuzzi);

    List<Suite> findByWifi(Boolean wifi);

    List<Suite> findBySeaView(Boolean seaView);

    List<Suite> findByParking(Boolean parking);

    List<Suite> findByAirConditioning(Boolean airConditioning);

    List<Suite> findByKitchenFacilities(Boolean kitchenFacilities);

    List<Suite> findByTv(Boolean tv);

    List<Suite> findByNumberBeds(Integer numberBeds);
}
