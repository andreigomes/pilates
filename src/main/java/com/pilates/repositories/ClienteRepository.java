package com.pilates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pilates.models.Cliente;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional(readOnly = true) //evita lock no banco
    Cliente findByEmail(String email);

    @Transactional(readOnly = true) //evita lock no banco
    Cliente findBycpfOuCnpj(String cpfOuCnpj);
}
