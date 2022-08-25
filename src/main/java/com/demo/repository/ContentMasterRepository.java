package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.ContentMaster;

@Repository
public interface ContentMasterRepository extends JpaRepository<ContentMaster, Long>{

}
