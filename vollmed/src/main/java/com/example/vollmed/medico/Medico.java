package com.example.vollmed.medico;

import com.example.vollmed.endereco.DadosEndereco;
import com.example.vollmed.endereco.Endereco;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

//table cria a tabela medicos no meu banco de dados
@Table(name = "medicos")
@Entity(name = "Medicos")
@Getter
@NoArgsConstructor // gera o contrutor defult sem argumentos
@AllArgsConstructor // serve para ter um construtor com todos os campos caso necessario
@EqualsAndHashCode (of = "id")//para ter o equals e o hscode no id
public class Medico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Embedded //serve para que o endereco fique em uma classe separada, mas faca parte da mesma tabela de medicos junto ao banco de dados
    private Endereco endereco;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    // esse metodo foi criado para satisfazer as exigencias da funçao do crud de atualizar dados
    // pois os dados que serao atualizados sao apenas os que estao nesse metodo

    public void atualizarInformacoes(DadosAtualizacaoMedicos dados) {
        if (dados.nome() != null){
            this.nome = dados.nome();
        }
        if (dados.telefone() != null){
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());

        }
    }
//    metodo criado para a funcao de desativar um usuario ao inves de excluir de forma permanente
    public void excluir() {
        this.ativo = false;
    }
}
