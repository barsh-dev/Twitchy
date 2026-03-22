package com.barshdev.twitchy.repository;

import com.barshdev.twitchy.repository.model.UrlHashMappingDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlHashMappingRepository extends JpaRepository<UrlHashMappingDataModel, Long> {
    UrlHashMappingDataModel findByHashCode(String hashCode);
}
