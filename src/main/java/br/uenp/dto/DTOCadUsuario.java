package br.uenp.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;


public record DTOCadUsuario(


@NotBlank String nome,
@NotBlank String email,
@NotBlank String senha,
@NotBlank String emailalternativo,
Date datanasc

) {
    
}