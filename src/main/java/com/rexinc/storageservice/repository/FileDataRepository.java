package com.rexinc.storageservice.repository;

import com.rexinc.storageservice.model.entity.FileData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileDataRepository extends JpaRepository<FileData, Long> {

    Optional<FileData> findByName(String name);

    Optional<FileData> findByImageReference(String reference);
}
