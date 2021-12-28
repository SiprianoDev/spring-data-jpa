package com.study.jpa.domain.entity;

import javax.persistence.*;

@Entity
@Table( name = "cliente" )//Aqui é necessário o @Table quando o nome da classe é diferente do nome da entidade
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//h2 db utiliza o AUTO
    @Column( name = "id" )//não precisaria pois os nomes são iguais da classe e da entidade
    private Integer id;
    @Column( name = "nome", length = 100) //mesmo tanto que varchar la no banco, denovo não precisava pois
    // ao colocar @Entity o JPA ja sabe que são colunas, mas precisa se os nomes dos atributos e das entidadades
    // nos bancos de dados forem diferentes
    private String nome;

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
