package br.com.mateusgustavo.springrest.service;

import br.com.mateusgustavo.springrest.exception.NotFoundException;
import br.com.mateusgustavo.springrest.model.MarcaModel;
import br.com.mateusgustavo.springrest.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository repository;

    public MarcaModel findById(long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException(null));
    }

    public List<MarcaModel> findAll(){ return repository.findAll(); }

    public List<MarcaModel> findByNome(String nome) { return repository.findByNome(nome); }

    public MarcaModel save(MarcaModel model){
        return repository.save(model);
    }

    public MarcaModel update(MarcaModel model){
        MarcaModel found = repository.findById(model.getId()).orElseThrow(() -> new NotFoundException(null));
        found.setNome(model.getNome());
        return repository.save(found);
    }

    public void delete(long id){
        MarcaModel found = repository.findById(id).orElseThrow(() -> new NotFoundException(null));
        repository.delete(found);
    }
}
