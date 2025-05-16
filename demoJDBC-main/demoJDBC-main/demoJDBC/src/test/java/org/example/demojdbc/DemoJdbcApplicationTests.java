package org.example.demojdbc;

import jakarta.transaction.Transactional;
import org.example.demojdbc.model.Persona;
import org.example.demojdbc.repository.PersonaRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Sql({"/schema.sql"})
class DemoJdbcApplicationTests {

    @Autowired
    PersonaRepository repositorio;

    @Test
    @Order(1)
    void borrarTodos() {
        repositorio.borrarTodos();
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(0, lista.size());
    }

    @Test
    @Order(2)
    void insertarPersona() {
        Persona persona = new Persona("pedro", "perez", 18);
        repositorio.insertar(persona);
        persona = new Persona("javier", "sanchez", 20);
        repositorio.insertar(persona);
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    @Order(3)
    void buscarTodos() {
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(2, lista.size());
    }

  @Test
  @Order(4)
    void borrarPersona() {
        Persona persona = new Persona("pedro");
        repositorio.borrar(persona);
        List<Persona> lista = repositorio.buscarTodos();
        assertEquals(1, lista.size());

    }

    @Test
    @Order(5)
    void buscarUno() {
        Persona persona = repositorio.buscarUno("javier");
        assertEquals("javier", persona.getNombre());
    }


}
