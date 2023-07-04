package com.matera.agenda.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.matera.agenda.domain.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {
	

}
