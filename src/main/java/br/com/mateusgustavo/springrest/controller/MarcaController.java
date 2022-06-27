package br.com.mateusgustavo.springrest.controller;

import br.com.mateusgustavo.springrest.model.MarcaModel;
import br.com.mateusgustavo.springrest.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marca/v1")
public class MarcaController {

    @Autowired
    MarcaService service;

    @GetMapping(produces = {"application/json", "application/xml"})
    public List<MarcaModel> findAll() { return service.findAll(); }

    @GetMapping(value = "/find/{nome}", produces = {"application/jason", "application/xml"})
    public List<MarcaModel> findByNome(@PathVariable("nome") String nome){
        return service.findByNome("%" + nome + "%");
    }

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public MarcaModel findById(@PathVariable("id") long id){
        return service.findById(id);
    }

    @PostMapping(produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public MarcaModel save(@RequestBody MarcaModel model){
        return service.save(model);
    }

    @PutMapping(produces = {"application/xml", "application/json"},
            consumes = {"application/xml", "application/json"})
    public MarcaModel update(@RequestBody MarcaModel model){
        return  service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }


}
