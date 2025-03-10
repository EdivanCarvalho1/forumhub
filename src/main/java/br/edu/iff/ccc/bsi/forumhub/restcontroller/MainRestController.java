package br.edu.iff.ccc.bsi.forumhub.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/v1")
@Tag(name = "MainRest", description = "Exemplo de um RestController")
public class MainRestController {

	@GetMapping(path = "/exemplo")
	@Operation(summary = "Retorna todos os exemplos armazenados")
	@ApiResponses({ @ApiResponse(responseCode = "200")

	})
	public ResponseEntity<List<Map<String, String>>> getExemplo() {
		List<Map<String, String>> listaExemplo = new ArrayList<>();
		Map<String, String> item1 = new HashMap<>();
		Map<String, String> item2 = new HashMap<>();
		item1.put("id", "1");
		item1.put("nome", "Edivan");
		item2.put("id", "2");
		item2.put("nome", "Carvalho");

		listaExemplo.add(item1);
		listaExemplo.add(item2);

		return ResponseEntity.ok().header("Content-Type", "application/json").body(listaExemplo);
	}

	@Operation(summary = "Retorna um exemplo por Id")
	@GetMapping(path = "/exemplos/{id}")
	public ResponseEntity<String> getExemploId(@PathVariable("id") int idx) {
		return ResponseEntity.ok().header("Content-Type", "application/json").body("Exemplo -> " + idx);
	}

	@Operation(summary = "Atualiza um exemplo por id")
	@PutMapping(path = "/exemplos/{id}")
	public String getExemploIdParam(@PathVariable("id") int id, @RequestParam("nome") String nome) {
		return "Id ->" + id + " nome ->" + nome;
	}

}
