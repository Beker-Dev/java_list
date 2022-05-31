package br.com.uniamerica.api.controller;

import br.com.uniamerica.api.entity.Especialidade;
import br.com.uniamerica.api.service.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping("/api/especialidades")
public class EspecialidadeController {

    @Autowired
    private EspecialidadeService especialidadeService;

    @GetMapping("/{idEspecialidade}")
    public ResponseEntity<Optional<Especialidade>> findById(
            @PathVariable("idEspecialidade") Long idEspecialidade
    ){
        return ResponseEntity.ok().body(this.especialidadeService.findById(idEspecialidade));
    }

    @GetMapping
    public ResponseEntity<Page<Especialidade>> listByAllPage(
            Pageable pageable
    ){
        return ResponseEntity.ok().body(this.especialidadeService.listAll(pageable));
    }

    @PostMapping
    public ResponseEntity<?> insert(
            @RequestBody Especialidade especialidade
    ){
        try {
            this.especialidadeService.insert(especialidade);
            return ResponseEntity.ok().body("Especialidade Cadastrada com Sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/{idEspecialidade}")
    public ResponseEntity<?> update(
            @PathVariable Long idEspecialidade,
            @RequestBody Especialidade especialidade
    ){
        try {
            this.especialidadeService.update(idEspecialidade, especialidade);
            return ResponseEntity.ok().body("Especialidade Atualizada com Sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PutMapping("/desativar/{idEspecialidade}")
    public ResponseEntity<?> desativar(
            @PathVariable Long idEspecialidade,
            @RequestBody Especialidade especialidade
    ){
        try {
            this.especialidadeService.desativar(idEspecialidade, especialidade);
            return ResponseEntity.ok().body("Especialidade Desativada com Sucesso.");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}