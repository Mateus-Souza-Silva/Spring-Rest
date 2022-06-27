package br.com.mateusgustavo.springrest.repository;

import br.com.mateusgustavo.springrest.model.MarcaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarcaRepository extends JpaRepository<MarcaModel, Long> {
    @Query(value = "SELECT * FROM marca ORDER BY NOME", nativeQuery = true)
    public List<MarcaModel> findAll();

    @Query(value = "SELECT * FROM marca where upper(marca) like upper(:nome) order by nome", nativeQuery = true)
    public List<MarcaModel> findByNome(@Param("nome") String nome);
}
