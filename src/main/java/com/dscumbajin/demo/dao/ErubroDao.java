package com.dscumbajin.demo.dao;

import com.dscumbajin.demo.entity.Erubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ErubroDao extends JpaRepository<Erubro,Integer> {
 List<Erubro> findByCodpro(String codpro);
 List<Erubro> findByCodproIn(List<String> codpros);
 }
