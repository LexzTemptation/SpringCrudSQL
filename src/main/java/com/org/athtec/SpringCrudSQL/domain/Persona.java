package com.org.athtec.SpringCrudSQL.domain;

import java.io.Serializable;

import lombok.Data;

import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.mongodb.core.mapping.Document;

@Data /*La notación "@Data" no es necesaria, pero es parte del proyecto lombok,
       * no tiene nada que ver con el famework de Spring, pero debido a que se utilizó,
       * se creó de manera automatica el estandar de los "JavaBeans", es decir, el
       * constructor vacío, métodos getters y setters, y los métodos toString, equals
       * y hashCode.
       * 
       * JavaBeans son clases que encapsulan muchos objetos en un solo objeto (el
       * bean). 
       * Es una clase java que debe seguir las siguientes convenciones:
       * 
       * -Debe implementar Serializable.
       * -Debe tener un constructor público sin argumentos.
       * -Todas las propiedades en java bean deben ser privadas con métodos getters y
       * setter públicos. */
@Document(collection = "persona")/* Aquí no es necesaria agregar esta notación pero en otros
                            sistemas operativos (como linux y mac) se tiene que
                            hacer para que no den error al momento de manejar el mapeo
                            de la tabla, esto sucede por la diferencia de mayusculas y minusculas
                            en la base de datos, la tabla "persona" tiene la primer letra
                            escrita con minusculas y en la clase de java está con mayuscula.*/
public class Persona implements Serializable
{
    private static final long serialVersionUID = 1L;

    @BsonId
    private long id;
    private String nombre, apellido, email, telefono;
}
