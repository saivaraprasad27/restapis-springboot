package com.learn.DecProjectModule.repository;

import com.learn.DecProjectModule.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByTitle(String title);
}
