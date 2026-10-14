package br.uenp.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.uenp.bancodedados.Usuario;
import br.uenp.dto.DTOCadUsuario;
import br.uenp.dto.DTOUpdateUsuario;
import br.uenp.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuario")
@CrossOrigin(origins = "http://localhost:8090" )
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;


    @PostMapping("/cad")
    @Transactional
    public ResponseEntity<Usuario> cadastrar(@RequestBody @Valid DTOCadUsuario dados) throws ParseException {
        var dadosLoc = new Usuario(dados);
		BeanUtils.copyProperties(dados, dadosLoc);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(dadosLoc));

    }

    
    @SuppressWarnings("null")
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> listar() {
        
		List<Usuario> dadosList = repository.findAll();
		if(!dadosList.isEmpty()) {
			for(Usuario dadosLoc : dadosList) {
				Long id = dadosLoc.getPkidusuario();
				dadosLoc.add(linkTo(methodOn(UsuarioController.class).getOneProduct(id)).withSelfRel());
			}
		}
		return ResponseEntity.status(HttpStatus.OK).body(dadosList);
           
    }


    @SuppressWarnings("null")
    @GetMapping("/lista/{id}")
	public ResponseEntity<Object> getOneProduct(@PathVariable(value="id") Long id){
		Optional<Usuario> ap = repository.findById(id);
		if(ap.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario not found.");
		}
		ap.get().add(linkTo(methodOn(UsuarioController.class).listar()).withRel("Usuario List"));
		return ResponseEntity.status(HttpStatus.OK).body(ap.get());
	}


    @PutMapping("/atualizar/{id}")
    @Transactional
    public void atualizarUsuario(@RequestBody @Valid DTOUpdateUsuario dados) throws ParseException{
        @SuppressWarnings("null")
        var user = repository.getReferenceById(dados.pkidusuario());
        user.atualizarUsuario(dados);
    }

    //como eu atualizaria alguma outra informação, exemplo, ativar um usuario via url, digitando "/ativarCliente/{id}" nesta mesma classe

    @PutMapping("/desabilitar/{id}")
    @Transactional
    public void desabilitar(@PathVariable Long id) {

        var dado = repository.getReferenceById(id);
        dado.desabilitar();
    }

    @PutMapping("/habilitar/{id}")
    @Transactional
    public void habilitar(@PathVariable Long id) {

        var dado = repository.getReferenceById(id);
        dado.habilitar();
    }


    @DeleteMapping("/apagar/{id}")
    @Transactional
    public void excluirUsuario(@PathVariable Long id){
        @SuppressWarnings("null")
        var user = repository.getReferenceById(id);
        repository.delete(user);
    }
}