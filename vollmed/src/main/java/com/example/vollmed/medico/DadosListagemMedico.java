package com.example.vollmed.medico;

// esse dto, foi criado para devolver no metodo de listar apenas as informaçoes solicitadas
// se eu estivesse colocado a entidade medico, todos os atributos dela seriam devolvidos na listagem
public record DadosListagemMedico(
        //esse DTO foi criado para a listagem dos medicos pois no enunciado pedia essas variveis especificas
        Long id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade) {

    // esse construtor foi inicializado para poder retornar os dados na listagem
    public DadosListagemMedico(Medico medico){
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());

    }
}
