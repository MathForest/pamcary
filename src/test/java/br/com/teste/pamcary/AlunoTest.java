package br.com.teste.pamcary;

import br.com.teste.pamcary.model.Aluno;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PamcaryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AlunoTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() { }

    @Test
    public void testGetAllAluno() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/aluno",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetAlunoById() {
        Aluno aluno = restTemplate.getForObject(getRootUrl() + "/aluno/6", Aluno.class);
        assertNotNull(aluno);
    }

    @Test
    public void testCreateAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("test");
        aluno.setIdade(10);
        ResponseEntity<Aluno> postResponse = restTemplate.postForEntity(getRootUrl() + "/aluno", aluno, Aluno.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateAluno() {
        int id = 1;
        Aluno aluno = restTemplate.getForObject(getRootUrl() + "/aluno/" + id, Aluno.class);

        aluno.setNome("test");
        aluno.setIdade(10);

        restTemplate.put(getRootUrl() + "/aluno/" + id, aluno);
        Aluno updatedAluno = restTemplate.getForObject(getRootUrl() + "/aluno/" + id, Aluno.class);
        assertNotNull(updatedAluno);
    }

    @Test
    public void testDeleteAluno() {
        int id = 4;
        Aluno aluno = restTemplate.getForObject(getRootUrl() + "/aluno/" + id, Aluno.class);
        assertNotNull(aluno);
        restTemplate.delete(getRootUrl() + "/aluno/" + id);
        try {
            aluno = restTemplate.getForObject(getRootUrl() + "/aluno/" + id, Aluno.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}