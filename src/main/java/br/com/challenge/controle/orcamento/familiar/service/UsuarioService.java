package br.com.challenge.controle.orcamento.familiar.service;

import br.com.challenge.controle.orcamento.familiar.model.Usuario;
import br.com.challenge.controle.orcamento.familiar.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).get();
    }

    public Usuario usuarioLogado(){
        return usuarioRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).get();
    }

}
