package com.merfolkstation.grocery.tracker.spring.model.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.List;
import java.util.Optional;

// This is .java, because kotlin won't work here (interoperability issues)
@NoRepositoryBean
public interface SoftDeleteRepository<T, ID> extends JpaRepository<T, ID> {

    // Override default methods to filter deleted entities
    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    List<T> findAll();

    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NULL")
    Page<T> findAll(Pageable pageable);

    @Override
    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.deletedAt IS NULL")
    Optional<T> findById(ID id);

    // Add methods to access deleted entities if needed
    @Query("SELECT e FROM #{#entityName} e WHERE e.deletedAt IS NOT NULL")
    List<T> findDeleted();

    @Query("SELECT e FROM #{#entityName} e")
    List<T> findAllIncludingDeleted();
}