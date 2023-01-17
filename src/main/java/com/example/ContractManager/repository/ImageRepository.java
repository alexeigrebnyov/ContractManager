package com.example.ContractManager.repository;

import com.example.ContractManager.model.ImageContract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageRepository extends JpaRepository<ImageContract, Long> {
}
