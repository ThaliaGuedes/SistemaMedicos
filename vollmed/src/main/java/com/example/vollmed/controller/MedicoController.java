package com.example.vollmed.controller;

import com.example.vollmed.medico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired
    private MedicoRepositoy repositoy;
    @RequestMapping
    @Transactional // CREATE - criar
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados){
        repositoy.save(new Medico(dados));
    }
    // READ - listar
    @GetMapping   /// sem parametro, la no insomina ele lista todos os medicos que estao cadastrados
    //com Paramentro Pageable paginacao ele devolve informacao como paginacao e permite trazer os dados para o front
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
       // esse retorna todos os medicos, ativos ou nao return repositoy.findAll(paginacao).map(DadosListagemMedico::new);
        return repositoy.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    // esse metodo comentado e interessante quando for necessario listar todos
//    public List<DadosListagemMedico> listar(){
//        return repositoy.findAll().stream().map(DadosListagemMedico::new).toList();
//    }

    //Update - Atualizar
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedicos dados){
        var medico = repositoy.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);
    }

    // DELETE - excluir

    //metodo para deletar de forma permanente
//    @DeleteMapping("/{id}")
//    @Transactional
//    public void excluir(@PathVariable Long id){
//        repositoy.deleteById(id);
//    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        var medico = repositoy.getReferenceById(id);
        medico.excluir();
        // quando solicitado, vai incluir false ao inves de excluir um dado de forma permanente
    }



}
