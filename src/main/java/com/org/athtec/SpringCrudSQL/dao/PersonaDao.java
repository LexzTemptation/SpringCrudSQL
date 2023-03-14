/* Data Access Object*/
package com.org.athtec.SpringCrudSQL.dao;

import org.springframework.data.repository.CrudRepository;

import com.org.athtec.SpringCrudSQL.domain.Persona;

/*Interface: Otra forma de lograr la abstracción en Java es con interfaces.
Una interfaz es una "clase completamente abstracta" que se utiliza para agrupar
métodos relacionados con cuerpos vacíos. 
  
CrudRepository: CrudRepository contiene métodos para operaciones CRUD.
Proporciona una operación Crud genérica en un repositorio. 
Se define en el paquete org.springframework.data.repository y amplía la interfaz
Spring Data Repository. Si alguien quiere usar CrudRepository en la aplicación
Spring Boot, debe crear una interfaz y ampliar la interfaz de CrudRepository. 
  
  */
public interface PersonaDao extends CrudRepository<Persona, Long>
{

}
