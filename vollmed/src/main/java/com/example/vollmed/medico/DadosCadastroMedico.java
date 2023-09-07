package com.example.vollmed.medico;

import com.example.vollmed.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

//aqui que fazemos as validaçoes para nao serem inseridas nullas
public record DadosCadastroMedico(
        //Esse dto foi criado para realizar o cadastro medico, pois no enunciado pedia todos os atributos aui existentes
        @NotBlank //verifica se o valor nao e null e tambem se nao e em branco
        String nome,
        @NotBlank // verifica se nao e nullo e nem esta em branco
        @Email // verifica se o email e valido, se possui @
        String email,
        @NotBlank
        @Pattern(regexp = "\\d{9,11}")
        String telefone,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}") // o crm e de punho obrigatorio, como um cpf
                // ele precisa ter 6 digitos e é necessario que seja numeros, entao pra isso usamos
                // o Pattern para informar a quantidade de digitos que estasmos esperando
        String crm,
        @NotNull
        Especialidade especialidade,
        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
