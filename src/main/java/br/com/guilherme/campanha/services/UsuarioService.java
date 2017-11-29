package br.com.guilherme.campanha.services;

import br.com.guilherme.campanha.entity.Campanha;
import br.com.guilherme.campanha.entity.Usuario;
import br.com.guilherme.campanha.repositories.CampanhaRepository;
import br.com.guilherme.campanha.repositories.TimeRepository;
import br.com.guilherme.campanha.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CampanhaRepository campanhaRepository;


    @RequestMapping(method = RequestMethod.GET, value = "/{usuarioId}")
    public Usuario getUsuario(@PathVariable Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if(usuario != null && usuario.getTime() != null)
            usuario.setCampanhas(campanhaRepository.findByTimeId(usuario.getTime().getId()));

        return usuario;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Usuario> getUsuario() {
        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        usuarios.forEach(usuario -> {
            if(usuario != null && usuario.getTime() != null)
                usuario.setCampanhas(campanhaRepository.findByTimeId(usuario.getTime().getId()));
        });
        
        return usuarios;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Usuario addUsuario(@RequestBody Usuario usuario) {
        Usuario u = usuarioRepository.findByEmail(usuario.getEmail());
        if(u != null) {
            throw new RestClientException("Já existe um usuário com esse e-mail.");
        }
        usuarioRepository.save(usuario);
        if(usuario.getTime() != null)
            usuario.setCampanhas(campanhaRepository.findByTimeId(usuario.getTime().getId()));
        return usuario;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{campanhaId}")
    public Campanha deleteCampanha(@PathVariable Long campanhaId) {
        Campanha campanha = campanhaRepository.findById(campanhaId).get();
        campanhaRepository.deleteById(campanhaId);
        return campanha;
    }

}
