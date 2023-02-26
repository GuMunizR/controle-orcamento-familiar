package br.com.challenge.controle.orcamento.familiar.service;

import br.com.challenge.controle.orcamento.familiar.dto.AtualizaDespesaDto;
import br.com.challenge.controle.orcamento.familiar.dto.DespesaDto;
import br.com.challenge.controle.orcamento.familiar.form.DespesaForm;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import br.com.challenge.controle.orcamento.familiar.repository.DespesaRepository;
import br.com.challenge.controle.orcamento.familiar.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DespesaService {

    private DespesaRepository despesaRepository;

    private UsuarioService usuarioService;

    public List<DespesaDto> listar() {
        return despesaRepository.findAll(usuarioService.usuarioLogado().getId()).stream().map(DespesaDto::new).collect(Collectors.toList());
    }

    public List<DespesaDto> buscarPorDescricao(String descricao){
        return despesaRepository.findByDescricao(descricao,usuarioService.usuarioLogado().getId()).stream().map(DespesaDto::new).collect(Collectors.toList());
    }

    public List<DespesaDto> listarPorMes(Integer ano, Integer mes){
        return despesaRepository.buscarPorMes(ano, mes, usuarioService.usuarioLogado().getId()).stream().map(DespesaDto::new).collect(Collectors.toList());
    }

    public DespesaDto buscarPorId(Long id) {
        Optional<Despesa> despesaDto = despesaRepository.findById(id, usuarioService.usuarioLogado().getId());
        return (despesaDto.isPresent()) ? new DespesaDto(despesaDto.get()) : null;
    }

    @Transactional
    public DespesaDto cadastrar(DespesaForm despesaForm) {
        if (validarExiste(despesaForm.getDescricao(), despesaForm.getData().getMonthValue(),
                despesaForm.getData().getYear())) {
            return null;
        }
        Despesa despesa = despesaForm.toDespesa();
        despesa.setUsuario(usuarioService.usuarioLogado());
        despesaRepository.save(despesa);
        return new DespesaDto(despesa);

    }

    @Transactional
    public DespesaDto atualizar(Long id, AtualizaDespesaDto despesaDto) {
        Optional<Despesa> despesaOpt = despesaRepository.findById(id, usuarioService.usuarioLogado().getId());
        if (despesaOpt.isPresent() && !validarExiste(despesaDto.getDescricao(),
                despesaDto.getData().getMonthValue(), despesaDto.getData().getYear())) {
            Despesa despesa = despesaOpt.get();
            despesaDto.setDespesa(despesa);
            return new DespesaDto(despesa);
        }
        return null;
    }

    public Boolean deletar(Long id) {
        Optional<Despesa> despesDto = despesaRepository.findById(id, usuarioService.usuarioLogado().getId());
        if (despesDto.isPresent()) {
            despesaRepository.delete(despesDto.get());
            return true;
        }
        return false;
    }

    public Boolean validarExiste(String descricao, Integer mes, Integer ano) {
        Optional<Despesa> receitaOpt = despesaRepository.buscarPorDescricaoEData(descricao, mes, ano, usuarioService.usuarioLogado().getId());
        return receitaOpt.isPresent();
    }
}
