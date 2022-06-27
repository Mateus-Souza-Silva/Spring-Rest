package br.com.mateusgustavo.springrest.repository;

import br.com.mateusgustavo.springrest.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {
    @Query(value = "SELECT * FROM PRODUTO ORDER BY DESCRICAO", nativeQuery = true)
    public List<ProdutoModel> findAll();

    public List<ProdutoModel> findByDescricaoContainingIgnoreCase(String descricao);

    @Query(value = "SELECT p.* FROM PRODUTO p, MARCA m WHERE p.marca_id = m.id AND " +
            "UPPER(m.nome) LIKE UPPER(:marcaNome) ORDER BY descricao", nativeQuery = true)
    public List<ProdutoModel> findByMarcaNome(@Param("marcaNome") String marcaNome);
}
