package com.library.controller;

import com.library.dto.BorrowingDTO;
import com.library.model.Borrowing;
import com.library.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {

    @Autowired
    private BorrowingService borrowingService;

    @GetMapping
    public ResponseEntity<List<Borrowing>> getAllBorrowings() {
        List<Borrowing> borrowings = borrowingService.getAllBorrowings();
        return ResponseEntity.ok(borrowings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Borrowing> getBorrowingById(@PathVariable Long id) {
        Borrowing borrowing = borrowingService.getBorrowingById(id);
        return ResponseEntity.ok(borrowing);
    }

    @PostMapping
    public ResponseEntity<Borrowing> createBorrowing(@RequestBody BorrowingDTO borrowingDTO) {
        Borrowing created = borrowingService.createBorrowing(borrowingDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Borrowing> returnBook(@PathVariable Long id) {
        Borrowing returned = borrowingService.returnBook(id);
        return ResponseEntity.ok(returned);
    }

    @GetMapping("/member/{memberName}")
    public ResponseEntity<List<Borrowing>> getBorrowingsByMember(@PathVariable String memberName) {
        List<Borrowing> borrowings = borrowingService.getBorrowingsByMember(memberName);
        return ResponseEntity.ok(borrowings);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Borrowing>> getActiveBorrowings() {
        List<Borrowing> borrowings = borrowingService.getActiveBorrowings();
        return ResponseEntity.ok(borrowings);
    }
}
