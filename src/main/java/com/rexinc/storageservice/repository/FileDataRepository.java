package com.rexinc.storageservice.repository;

import com.rexinc.storageservice.model.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

    Optional<FileData> findByName(String name);

    Optional<FileData> findByImageReference(String reference);
}
