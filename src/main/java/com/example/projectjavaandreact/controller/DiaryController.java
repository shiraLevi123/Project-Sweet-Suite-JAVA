package com.example.projectjavaandreact.controller;

import com.example.projectjavaandreact.dto.DiaryDTO;
import com.example.projectjavaandreact.model.Diary;
import com.example.projectjavaandreact.model.Suite;
import com.example.projectjavaandreact.service.DiaryRepository;
import com.example.projectjavaandreact.service.SuiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/diary")
@CrossOrigin
public class DiaryController {

    @Autowired
    private SuiteRepository suiteRepository;
    @Autowired
    private DiaryRepository diaryRepository;

    // קבלת כל ההזמנות
    @GetMapping
    public List<Diary> getAllDiaryEntries() {
        return diaryRepository.findAll(); // קבלת כל ההזמנות
    }

    // קבלת הזמנה לפי ID
    @GetMapping("/{id}")
    public Diary getDiaryEntryById(@PathVariable Long id) {
        return diaryRepository.findById(id).orElse(null); // קבלת הזמנה לפי ID
    }

    @PostMapping
    public ResponseEntity<Object> createDiaryEntry(@RequestBody DiaryDTO diaryDTO) {
        // מצא את הסוויטה על פי ה-ID
        Suite suite = suiteRepository.findById(diaryDTO.getSuiteId()).orElse(null);

        if (suite != null) {
            // בדוק אם יש חפיפות בתאריכים
            List<Diary> conflictingBookings = diaryRepository.findConflictingBookings(
                    diaryDTO.getSuiteId(), diaryDTO.getCheckIn(), diaryDTO.getCheckOut());

            // אם יש חפיפות, החזר שגיאה
            if (!conflictingBookings.isEmpty()) {
                return new ResponseEntity<>( "There is a conflict with the selected dates!", HttpStatus.BAD_REQUEST);
            }

            // אם אין חפיפות, צור את היומן החדש
            Diary diary = new Diary();
            diary.setSuite(suite);
            diary.setCheckIn(diaryDTO.getCheckIn());
            diary.setCheckOut(diaryDTO.getCheckOut());

            return new ResponseEntity<>(diaryRepository.save(diary), HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public Diary updateDiaryEntry(@PathVariable Long id, @RequestBody Diary diary) {
        diary.setId(id); // הגדרת ה-ID של ההזמנה המעודכנת
        return diaryRepository.save(diary); // שמירת ההזמנה המעודכנת
    }

    // מחיקת הזמנה
    @DeleteMapping("/{id}")
    public void deleteDiaryEntry(@PathVariable Long id) {
        diaryRepository.deleteById(id); // מחיקת הזמנה לפי ID
    }
}
