package com.study.jpa;

import com.study.jpa.domain.entity.Cliente;
import com.study.jpa.domain.repositorio.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {

    @Bean                       //não tem necessidade do @Autowired
    public CommandLineRunner init(@Autowired Clientes clientes){//traz do IOC uma instancia do cliente
        return args -> {
            System.out.println("Salvando Clientes");
            clientes.salvar(new Cliente("Anderson Sipriano"));
            clientes.salvar(new Cliente("Gabrielle Ferreira"));

            List<Cliente> todosClientes = clientes.obterTodos();
            todosClientes.forEach(System.out::println);//vai imprimir com toString

            System.out.println("\nAtualizando Clientes");
            todosClientes.forEach(c -> {
                c.setNome(c.getNome() + " Atualizado");
                clientes.atualizar(c);
            });

            todosClientes.forEach(System.out::println);

            System.out.println("\nBuscando Clientes por Nome");
            clientes.buscarPorNome("Sip").forEach(System.out::println);

//            System.out.println("\nDeletando Todos os Clientes");
//            clientes.obterTodos().forEach(c -> clientes.deletar(c));
            

            if (clientes.obterTodos().isEmpty()){
                System.out.println("Nenhum Cliente Encontrado!");
            } else {
                todosClientes.forEach(System.out::println);
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}