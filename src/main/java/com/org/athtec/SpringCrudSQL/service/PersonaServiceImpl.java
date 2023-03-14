package com.org.athtec.SpringCrudSQL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.org.athtec.SpringCrudSQL.dao.PersonaDao;
import com.org.athtec.SpringCrudSQL.domain.Persona;

@Service /*Los componentes de servicio son el archivo de clase que contiene la
          * anotación @Service. Estos archivos de clase se utilizan para escribir la
          * lógica empresarial en una capa diferente, separada del archivo de
          * clase @RestController
          */
public class PersonaServiceImpl implements PersonaService
{

    @Autowired /* Para poder inyectar cualquier otra dependencia/objeto que sea administrado
                * por el contenedor
                * se utiliza la notación "@Autowired", muy similar a "Inject"
                */
    private PersonaDao personaDao;

    /* Cuando se está dentro de la capa DAO se maneja el concepto de transacciones,
     * esto quiere decir que cualquier operación con la base de datos en dado caso
     * de error se va a hacer un rollback o en caso de que todo sea exitoso se
     * hace un commit, pero cuando se está trabajando con las clases de servicio
     * se podría estar utilizando varios objetos de tipo DAO desde una misma clase
     * de servicio, por lo tanto se podrían estar realizando varias operaciones sobre
     * distintas tablas de la base de datos, estos métodos se deben estar marcando
     * como transaccionales, ya que en dado caso de error se debe estar haciendo un
     * rollback y no guardar ninguna información de ninguna de las tablas afectadas,
     * y en caso de que todo sea exitoso, entonces debe hacer un commit de toda la
     * transacción guardando toda la información en todas las tablas afectadas,
     * dependiendo del tipo de método es el tipo de transacción que se va a realizar.
     */

    @Override
    @Transactional(readOnly = true) /* Como se va hacer solo listado,
                                        unicamente se lee información de la BD,
                                        se agrega la notación "@Transactional" proveniente de
                                        spring, y como solo este método va a leer información,
                                        se utiliza el atributo de "readOnly" y se usa el valor
                                        de "true".*/
    public List<Persona> listarPersonas()
    {
        return (List<Persona>) personaDao.findAll();
        /* Variable de personaDao y método de "findAll();", 
            este regresa un tipo object, por eso se hace cast
            para poderlo convertir a una lista */
    }

    @Override
    @Transactional() /* En este caso no tiene ningun otro parametro ya que va a guardar.
                        Este solo va a hacer commit o rollback. */
    public void guardar(Persona persona)
    {
        personaDao.save(persona);
        /* Se manda a llamar el objeto persona y se llama el
            método "save", y se proporciona el objeto persona
            que se desea guardar en la BD */
    }

    @Override
    @Transactional /* Igual que en guardar, este solo va a hacer commit o rollback. */
    public void eliminar(Persona persona)
    {
        personaDao.delete(persona);
        /* Se manda a llamar el objeto persona y se llama el
            método "delete", y se proporciona el objeto persona
            que se desea guardar en la BD */
    }

    @Override
    @Transactional(readOnly = true) /*Igual que en guardar persona. */
    public Persona encontrarPersona(Persona persona)
    {
        return personaDao.findById(persona.getId_persona()).orElse(null);
        /* Variable de personaDao y método de "findById();", 
            en este caso requiere y recibe la llave primaria "persona.getId_persona()", 
            y se hace el return del objeto encontrado.
            El método "findById();" regresa un tipo "Optional", nos da a decidir en dado
            caso que el valor regresado sea de tipo "null", esto es para evitar errores */
    }
    
}

