package com.app.cediamback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.cediamback.model.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
}
