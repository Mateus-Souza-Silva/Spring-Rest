package br.com.mateusgustavo.springrest.model;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produto")
public class ProdutoModel extends RepresentationModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    private MarcaModel marca;

    public ProdutoModel() {
    }

    public ProdutoModel(long id, String descricao, MarcaModel marca) {
        this.id = id;
        this.descricao = descricao;
        this.marca = marca;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MarcaModel getMarca() {
        return marca;
    }

    public void setMarca(MarcaModel marca) {
        this.marca = marca;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoModel produtoModel = (ProdutoModel) o;
        return id == produtoModel.id && Objects.equals(descricao, produtoModel.descricao) && Objects.equals(marca, produtoModel.marca);
    }

    @Override
    public int hashCode(){ return Objects.hash(id, descricao, marca);}
}
