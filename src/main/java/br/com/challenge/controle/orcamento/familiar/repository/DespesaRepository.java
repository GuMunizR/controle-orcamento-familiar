package br.com.challenge.controle.orcamento.familiar.repository;

import br.com.challenge.controle.orcamento.familiar.model.Despesa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {

    @Query("SELECT d FROM Despesa d WHERE d.descricao = :descricao AND d.usuario.id = :idUsuario")
    List<Despesa> findByDescricao(String descricao, Long idUsuario);

    @Query("SELECT d FROM Despesa d WHERE MONTH(d.data) = :mes AND YEAR(d.data) = :ano AND d.usuario.id = :idUsuario")
    List<Despesa> buscarPorMes(Integer ano, Integer mes, Long idUsuario);

    @Query("SELECT d FROM Despesa d WHERE MONTH(d.data) = :mes AND YEAR(d.data) = :ano AND d.usuario.id = :idUsuario")
    List<Despesa> resumoDespesaMes(Integer ano, Integer mes, Long idUsuario);
    @Query("SELECT d FROM Despesa d WHERE d.descricao = :descricao AND MONTH(d.data) = :mes AND YEAR(d.data) = :ano AND d.usuario.id = :idUsuario")
    Optional<Despesa> buscarPorDescricaoEData(String descricao, Integer mes, Integer ano, Long idUsuario);

    @Query("SELECT d FROM Despesa d WHERE d.usuario.id = :id")
    List<Despesa> findAll(Long id);

    @Query("SELECT d FROM Despesa d WHERE d.id = :id AND d.usuario.id = :idUsuario")
    Optional<Despesa> findById(Long id, Long idUsuario);

}
