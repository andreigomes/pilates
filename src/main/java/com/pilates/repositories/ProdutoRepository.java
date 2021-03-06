package com.pilates.repositories;

import com.pilates.models.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pilates.models.Produto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

//    @Query(
//        " SELECT "
//                + "    DISTINCT obj "
//        + " FROM Produto obj "
//        + " INNER JOIN obj.categorias cat "
//        + " WHERE "
//                + "   obj.name  LIKE %:nome% "
//                + "   AND cat IN (:categorias) "
//    )
//    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageable);

    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNameContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageable);

}
