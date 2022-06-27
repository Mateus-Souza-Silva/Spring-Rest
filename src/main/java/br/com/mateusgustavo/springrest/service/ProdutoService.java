package br.com.mateusgustavo.springrest.service;

import br.com.mateusgustavo.springrest.exception.NotFoundException;
import br.com.mateusgustavo.springrest.model.ProdutoModel;
import br.com.mateusgustavo.springrest.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;
    
    public List<ProdutoModel> findAll(){ return repository.findAll(); }
    public List<ProdutoModel> findByDescricao(String descricao) { return repository.findByDescricaoContainingIgnoreCase(descricao);}
    public List<ProdutoModel> findByMarcaNome(String marcaNome){
        return repository.findByMarcaNome("%" + marcaNome + "%");
    }

    public ProdutoModel findById(long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(null));
    }

    public ProdutoModel save(ProdutoModel model){
        return repository.save(model);
    }

    public ProdutoModel update(ProdutoModel model){
        ProdutoModel found = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException(null));
        found.setDescricao(model.getDescricao());
        found.setDescricao(model.getDescricao());
        return  repository.save(model);
    }

    public void delete(long id){
        ProdutoModel found = repository.findById(id).orElseThrow(() -> new NotFoundException(null));
        repository.delete(found);
    }
}
