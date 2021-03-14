package com.dscumbajin.demo.dao;

import com.dscumbajin.demo.entity.Rubro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RubroDao extends JpaRepository<Rubro,Integer> {
List<Rubro> findByCoderub(Integer coderub);
List<Rubro> findByCoderubIn(List<Integer> coderubs);
}
