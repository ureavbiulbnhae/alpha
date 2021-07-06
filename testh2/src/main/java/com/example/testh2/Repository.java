package com.example.testh2;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.testh2.model.School;

public interface Repository extends JpaRepository<School, Integer> {

	/*
	List<School> findByDistrict(String district);
	List<School> findByRegion(String region);*/
}
