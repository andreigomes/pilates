package com.pilates.repositories;

import com.pilates.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilates.models.Cidade;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> { 

}
