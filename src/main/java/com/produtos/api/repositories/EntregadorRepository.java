package com.produtos.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.produtos.api.domain.Entregador;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Integer> {

	@Query("SELECT obj FROM Entregador obj WHERE obj.cpf =:cpf")
	Entregador findByCPF(@Param("cpf") String cpf);

}
