package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.HeaderMaster;

@Repository
public interface HeaderMasterRepository extends JpaRepository<HeaderMaster, Long>{

}
