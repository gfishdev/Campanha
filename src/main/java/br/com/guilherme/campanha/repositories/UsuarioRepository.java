package br.com.guilherme.campanha.repositories;

import br.com.guilherme.campanha.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);
}
