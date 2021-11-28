package com.produtos.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.produtos.api.domain.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Integer>{

}
