package br.com.challenge.controle.orcamento.familiar.repository;


import br.com.challenge.controle.orcamento.familiar.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query("SELECT r FROM Receita r WHERE r.descricao = :descricao AND r.usuario.id = :idUsuario")
    List<Receita> findByDescricao(String descricao, Long idUsuario);

    @Query("SELECT r FROM Receita r WHERE MONTH(r.data) = :mes AND YEAR(r.data) = :ano AND r.usuario.id = :idUsuario")
    List<Receita> buscarPorMes(Integer ano, Integer mes, Long idUsuario);

    @Query("SELECT r FROM Receita r WHERE MONTH(r.data) = :mes AND YEAR(r.data) = :ano AND r.usuario.id = :idUsuario")
    List<Receita> resumoReceitaMes(Integer ano, Integer mes, Long idUsuario);
    @Query("SELECT r FROM Receita r WHERE r.descricao = :descricao AND MONTH(r.data) = :mes AND YEAR(r.data) = :ano AND r.usuario.id = :idUsuario")
    Optional<Receita> buscarPorDescricaoEData(String descricao, Integer mes, Integer ano, Long idUsuario);

    @Query("SELECT r FROM Receita r WHERE r.usuario.id = :idUsuario")
    List<Receita> findAll(Long idUsuario);

    @Query("SELECT r FROM Receita r WHERE r.id = :id AND r.usuario.id = :idUsuario")
    Optional<Receita> findById(Long id, Long idUsuario);

}
