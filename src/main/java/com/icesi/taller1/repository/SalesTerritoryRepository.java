package com.icesi.taller1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.icesi.taller1.model.sales.Salesterritory;

@Repository
public interface SalesTerritoryRepository extends JpaRepository<Salesterritory, Integer> {

}
