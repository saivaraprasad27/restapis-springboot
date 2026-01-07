package com.learn.DecProjectModule.repository;

import com.learn.DecProjectModule.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByTitle(String title);
}
