package br.uenp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.uenp.bancodedados.*;;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Page<Usuario> findAllByAtivoTrue(Pageable paginacao);
}
