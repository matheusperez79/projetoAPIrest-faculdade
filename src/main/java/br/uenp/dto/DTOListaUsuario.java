package br.uenp.dto;

import br.uenp.bancodedados.*;;

public record DTOListaUsuario(

        Long pkidusuario,
        String nome,
        String email,
        String senha,
        String emailalternativo

) {
    public DTOListaUsuario(Usuario u) {
        this(u.getPkidusuario(), u.getNome(), u.getEmail(), u.getSenha(), u.getEmailalternativo());
    }
}