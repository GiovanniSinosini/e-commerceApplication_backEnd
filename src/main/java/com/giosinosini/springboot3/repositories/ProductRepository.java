package com.giosinosini.springboot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giosinosini.springboot3.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}