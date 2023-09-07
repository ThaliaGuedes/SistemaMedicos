package com.example.vollmed.medico;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;

public interface MedicoRepositoy extends JpaRepository<Medico, Long> {
    //Page foi criado para o metodo de inativar medicos
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
