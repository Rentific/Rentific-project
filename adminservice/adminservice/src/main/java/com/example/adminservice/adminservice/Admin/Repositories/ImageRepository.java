package com.example.adminservice.adminservice.Admin.Repositories;

import java.util.Optional;

import com.example.adminservice.adminservice.Admin.Models.ImageModel;
import com.example.adminservice.adminservice.Admin.Models.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
    Optional<ImageModel> findByName(String name);
    Optional<ImageModel> findByRealEstate(RealEstate realEstate);
}
