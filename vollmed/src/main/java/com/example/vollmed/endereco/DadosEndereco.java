package com.example.vollmed.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        @Pattern(regexp = "\\d{8}") //cep e obrigatorio por isso usamos o regex
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,

        String numero,
        String complemento
) {

}
