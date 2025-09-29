package br.senac.tads.dsw.gerenciador.tarefas;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.*;

@Service
public class TarefaService {

    private Map<String, TarefaDTO> mapTarefas;

    public TarefaService(){
        mapTarefas = new HashMap<>();
        mapTarefas.put("tarefa", new TarefaDTO("Prova de DSW","Henrique José",LocalDate.parse("2025-09-29"),"Preparar o resumo da prova"));
    }

    public List<TarefaDTO> findAll() {
        return new ArrayList<>(mapTarefas.values());
    }

    public TarefaDTO addNew(TarefaDTO dto) {

        //Validações
        if (dto.getTitle().isEmpty() || dto.getResponsavel().isEmpty() || dto.getDataTermino() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Os campos de titulo, Responsavel e Data de Termino precisam ser preenchidos!");
        }

        if (dto.getDataTermino().isBefore(LocalDate.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A data de termino não pode ser inferior a data atual!");
        }

        mapTarefas.put(dto.getTitle(), dto);
        return dto;
    }

    public boolean atrasada(TarefaDTO dto){
        return dto.getDataTermino().isBefore(LocalDate.now());
    }

}
