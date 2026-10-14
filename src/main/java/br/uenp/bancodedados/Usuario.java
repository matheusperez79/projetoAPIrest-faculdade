package br.uenp.bancodedados;

import java.io.Serializable;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import br.uenp.dto.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
public class Usuario extends RepresentationModel<Usuario> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long pkidusuario;
    private String nome;
    private String email;
    private String senha;
    private Date datanasc;
    private String emailalternativo;
    private Boolean ativo;

    public Usuario(DTOCadUsuario dados) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.senha() != null) {
            this.senha = dados.senha();
        }
        if (dados.emailalternativo() != null) {
            this.emailalternativo = dados.emailalternativo();
        }

        this.datanasc = dados.datanasc();

        this.ativo = true;

    }

    public void atualizarUsuario(DTOUpdateUsuario dados) {
        if (dados.pkidusuario() != null) {
            if (dados.nome() != null) {
                this.nome = dados.nome();
            }
            if (dados.email() != null) {
                this.email = dados.email();
            }
            if (dados.senha() != null) {
                this.senha = dados.senha();
            }
            if (dados.emailalternativo() != null) {
                this.emailalternativo = dados.emailalternativo();
            }
        }
    }

    public void desabilitar() {
        this.ativo = false;
    }

    public void habilitar() {
        this.ativo = true;
    }

}