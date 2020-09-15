package br.com.teste.pamcary.controller;

import br.com.teste.pamcary.handler.AbstractRestHandler;
import br.com.teste.pamcary.handler.RequestException;
import br.com.teste.pamcary.handler.ResourceNotFoundException;
import br.com.teste.pamcary.model.Aluno;
import br.com.teste.pamcary.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/aluno")
public class AlunoController extends AbstractRestHandler {

    @Autowired
    private AlunoService service;

    @GetMapping
    public List<Aluno> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> findById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Aluno>(service.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException("ID informado não localizado");
        }
    }

    @PostMapping
    public ResponseEntity<Aluno> save(@RequestBody Aluno aluno) {
        try {
            return new ResponseEntity<Aluno>(service.save(aluno), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RequestException("Dados obrigatórios não informados");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> update(@PathVariable("id") Integer id, @RequestBody Aluno alunoUpdated) {
        try {
            return new ResponseEntity<Aluno>(service.update(id, alunoUpdated), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Aluno> delete(@PathVariable Integer id) {
        try {
            service.delete(id);
            return new ResponseEntity<Aluno>(HttpStatus.OK);
        } catch (NoSuchElementException e) {
            throw new ResourceNotFoundException();
        }
    }
}
