package com.fullstackalex.LibraryManagement.repository;

import com.fullstackalex.LibraryManagement.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
