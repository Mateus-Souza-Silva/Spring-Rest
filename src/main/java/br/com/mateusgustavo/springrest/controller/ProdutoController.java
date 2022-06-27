package br.com.mateusgustavo.springrest.controller;

import br.com.mateusgustavo.springrest.model.ProdutoModel;
import br.com.mateusgustavo.springrest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto/v1")
public class ProdutoController {
    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ProdutoModel findById(@PathVariable long id){
        ProdutoModel model = service.findById(id);
        buildEntityLink(model);
        return model;
    }

    @GetMapping(value = "/", produces = {"application/json", "application/xml"})
    public CollectionModel<ProdutoModel> findAll() {
        CollectionModel<ProdutoModel> collectionModel = CollectionModel.of(service.findAll());
        buildCollectionLink(collectionModel);
        return collectionModel;
    }

    @GetMapping(value = "/find", produces = {"application/json", "application/xml"})
    public List<ProdutoModel> findByTitulo(@RequestParam Optional<String> descricao,
                                       @RequestParam Optional<String> marcaNome) {
        List<ProdutoModel> result = new ArrayList<>();
        if (descricao.isPresent()) {
            result = service.findByDescricao(descricao.get());
        }
        if (marcaNome.isPresent()) {
            result = service.findByMarcaNome(marcaNome.get());
        }
        return result;
    }

    @PostMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ProdutoModel save(@RequestBody ProdutoModel model) {
        return service.save(model);
    }

    @PutMapping(produces = {"application/json", "application/xml"}, consumes = {"application/json", "application/xml"})
    public ProdutoModel update(@RequestBody ProdutoModel model) {
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public void buildEntityLink(ProdutoModel model) {
        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(ProdutoController.class).findById(model.getId())
                ).withRel(IanaLinkRelations.SELF)
        );
    }

    public void buildCollectionLink(CollectionModel<ProdutoModel> collectionModel) {

        for (ProdutoModel produto : collectionModel) {
            buildEntityLink(produto);
        }

        collectionModel.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(
                                ProdutoController.class
                        ).findAll()
                ).withRel(IanaLinkRelations.COLLECTION)
        );
    }
}
