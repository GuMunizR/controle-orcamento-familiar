package br.com.challenge.controle.orcamento.familiar.dto;

import br.com.challenge.controle.orcamento.familiar.model.enums.CategoriaEnum;
import br.com.challenge.controle.orcamento.familiar.model.Despesa;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AtualizaDespesaDto {

    @NotBlank
    private String descricao;

    @Pattern(regexp = "^\\d+(\\.\\d{2})?$")
    @NotBlank
    private String valor;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    @NotNull
    private LocalDateTime data;

    @NotBlank
    private String categoria;

    public void setDespesa(Despesa despesa) {
        despesa.setValor(new BigDecimal(valor));
        despesa.setDescricao(descricao);
        despesa.setData(data);
        despesa.setCategoria(CategoriaEnum.valueOf(categoria));
    }
}
