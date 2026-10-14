package br.uenp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DTOUpdateUsuario(
        @NotNull Long pkidusuario,
        @NotBlank String nome,
        @NotBlank String email,
        @NotBlank String senha,
        @NotBlank String emailalternativo

) {

}