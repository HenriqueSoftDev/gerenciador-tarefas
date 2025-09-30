package br.senac.tads.dsw.gerenciador.tarefas;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TarefaDTO {

    @NotBlank
    @Size(min = 3, max = 100)
    private String title;

    @NotBlank
    @Size(min = 1, max = 64)
    private String responsavel;

    @NotNull
    @FutureOrPresent
    private LocalDate dataTermino;

    @Size(max = 200)
    private String details;

    public TarefaDTO() {
    }

    public TarefaDTO(String title, String responsavel, LocalDate dataTermino, String details) {
        this.title = title;
        this.responsavel = responsavel;
        this.dataTermino = dataTermino;
        this.details = details;
    }

    public @NotBlank @Size(min = 3, max = 100) String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank @Size(min = 3, max = 100) String title) {
        this.title = title;
    }

    public @NotBlank @Size(min = 1, max = 64) String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(@NotBlank @Size(min = 1, max = 64) String responsavel) {
        this.responsavel = responsavel;
    }

    public   @NotNull @FutureOrPresent LocalDate getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(@NotNull @FutureOrPresent LocalDate dataTermino) {
        this.dataTermino = dataTermino;
    }

    public @Size(max = 200) String getDetails() {
        return details;
    }

    public void setDetails(@Size(max = 200) String details) {
        this.details = details;
    }

    @JsonProperty("atrasada")
    public boolean isAtrasada() {
        return dataTermino.isBefore(LocalDate.now());
    }
}
