package com.example.projectjavaandreact.service;

import com.example.projectjavaandreact.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DiaryRepository extends JpaRepository<Diary, Long> {

    // חיפוש הזמנות חפופות עבור סוויטה מסוימת
    @Query("SELECT d FROM Diary d WHERE d.suite.id = :suiteId AND (" +
            "(d.checkIn <= :checkOut AND d.checkOut >= :checkIn))")
    List<Diary> findConflictingBookings(@Param("suiteId") Long suiteId,
                                        @Param("checkIn") LocalDate checkIn,
                                        @Param("checkOut") LocalDate checkOut);
}
