package com.example.projectjavaandreact.controller;

import com.example.projectjavaandreact.model.Customer;
import com.example.projectjavaandreact.model.Diary;
import com.example.projectjavaandreact.service.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Map;

@RestController
    @RequestMapping("/api/customer")
    @CrossOrigin
    public class CustomerController {

        @Autowired
        private CustomerRepository customerRepository;

        // יצירת לקוח חדש
        @PostMapping("/add")
        public Customer addCustomer(@RequestBody Customer customer) {
            return customerRepository.save(customer);
        }
        

        // עדכון פרטי לקוח
        @PutMapping("/update/{id}")
        public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
            customer.setName(updatedCustomer.getName());
            customer.setEmail(updatedCustomer.getEmail());
            customer.setPassword(updatedCustomer.getPassword());
            return customerRepository.save(customer);
        }

        // מחיקת לקוח
        @DeleteMapping("/delete/{id}")
        public void deleteCustomer(@PathVariable Long id) {
            customerRepository.deleteById(id);
        }

        // קבלת רשימת הלקוחות
        @GetMapping("/all")
        public List<Customer> getAllCustomers() {
            return customerRepository.findAll();
        }

        // קבלת לקוח לפי ID
        @GetMapping("/{id}")
        public Customer getCustomerById(@PathVariable Long id) {
            return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Customer not found"));
        }
        @PostMapping("/signup")
        public ResponseEntity<?> signup(@RequestBody Customer customer) {
            // בדיקה אם האימייל כבר קיים
            if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email is already in use");
            }

            // שמירת הלקוח
            customerRepository.save(customer);

            return ResponseEntity.ok("Customer registered successfully");
        }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        // חיפוש הלקוח לפי אימייל
        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));

        // בדיקת הסיסמה
        if (!password.equals(customer.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }

        return ResponseEntity.ok("Login successful");
    }


}

