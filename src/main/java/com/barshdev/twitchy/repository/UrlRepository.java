package com.barshdev.twitchy.repository;

import com.barshdev.twitchy.repository.model.URLDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<URLDataModel, Long> {

}
