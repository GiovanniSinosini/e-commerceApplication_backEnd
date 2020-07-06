package com.giosinosini.springboot3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.giosinosini.springboot3.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
