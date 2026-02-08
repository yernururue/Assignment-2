package com.library.repository;

import com.library.model.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {

    List<Borrowing> findByMemberName(String memberName);

    List<Borrowing> findByStatus(String status);

    List<Borrowing> findByBookId(Long bookId);

    List<Borrowing> findByStatusNot(String status);
}
