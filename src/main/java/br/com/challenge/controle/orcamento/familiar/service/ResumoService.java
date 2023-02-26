package br.com.challenge.controle.orcamento.familiar.service;

import br.com.challenge.controle.orcamento.familiar.dto.ResumoDto;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import br.com.challenge.controle.orcamento.familiar.model.Receita;
import br.com.challenge.controle.orcamento.familiar.model.Usuario;
import br.com.challenge.controle.orcamento.familiar.repository.DespesaRepository;
import br.com.challenge.controle.orcamento.familiar.repository.ReceitaRepository;
import br.com.challenge.controle.orcamento.familiar.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ResumoService {

    private ReceitaRepository receitaRepository;
    private DespesaRepository despesaRepository;
    private UsuarioService usuarioService;

    public ResumoDto resumoDoMes(Integer ano, Integer mes) {
        List<Receita> listReceita =receitaRepository.resumoReceitaMes(ano, mes, usuarioService.usuarioLogado().getId());
        List<Despesa> listDespesa = despesaRepository.resumoDespesaMes(ano, mes, usuarioService.usuarioLogado().getId());
        return new ResumoDto(listReceita, listDespesa);
    }
}