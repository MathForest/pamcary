package br.com.teste.pamcary.service;

import br.com.teste.pamcary.model.Aluno;
import br.com.teste.pamcary.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> findAll() {
        return repository.findAll();
    }

    public Aluno findById(Integer id) {
        return repository.findById(id).get();
    }

    @Validated
    public Aluno save(@RequestBody Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno update(Integer id, Aluno AlunoUpdated) {
        Aluno aluno = repository.findById(id).get();
        aluno.setNome(AlunoUpdated.getNome());
        aluno.setIdade(AlunoUpdated.getIdade());
        return repository.save(aluno);
    }

    public void delete(Integer id) {
        Aluno aluno = repository.findById(id).get();
        repository.delete(aluno);
    }
}


