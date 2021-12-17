package com.study.jpa.domain.repositorio;

import com.study.jpa.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class Clientes {
    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "select * from cliente";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ?";

    @Autowired//cria conexão, e nativamente se comunica em SQL com o BD
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente){
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente){
        jdbcTemplate.update(UPDATE, new Object[] {
                cliente.getNome(), cliente.getId()
        });
        return cliente;
    }

    public void deletar(Cliente cliente){
        deletar(cliente.getId());
    }

    public void deletar(Integer id){
        jdbcTemplate.update(DELETE, new Object[]{id});
    }

    public List<Cliente> buscarPorNome(String nome){
        return jdbcTemplate.query(SELECT_ALL.concat(" where nome like ?"),
                new Object[]{"%" + nome + "%"},//faz com que retorne o nome mesmo tendo outra coisa antes ou depois
                obterClienteMapper());//acaba por retorna tudo q tem um "a" no meio caso digite um
    }

    public List<Cliente> obterTodos(){
        return jdbcTemplate.query(SELECT_ALL, obterClienteMapper());
    }

    private RowMapper<Cliente> obterClienteMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                String nome = rs.getString("nome");
                Integer id = rs.getInt("id");
                return new Cliente(id, nome);
            }
        };
    }
}
