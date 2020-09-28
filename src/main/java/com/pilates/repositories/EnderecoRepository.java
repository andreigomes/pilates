package com.pilates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilates.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> { 

}
