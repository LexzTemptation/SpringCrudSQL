/*2*/
package com.org.athtec.SpringCrudSQL.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.athtec.SpringCrudSQL.domain.Persona;
/* import com.org.athtec.SpringUdemy4.dao.PersonaDao; */
import com.org.athtec.SpringCrudSQL.service.PersonaService;

import lombok.extern.slf4j.Slf4j;


@Controller /* La anotación @Controller indica que la clase anotada es un controlador. Es
             * una especialización de @Component y se detecta automáticamente a través del
             * escaneo de classpath. Por lo general, se usa en combinación con métodos de
             * controlador anotados basados en la anotación @RequestMapping. @RestController
             * es una anotación de conveniencia para hermanos para crear controladores
             * Restful.
             */
@Slf4j /* Simple Logging Facade for Java (abreviado SLF4J) actúa como una fachada para
                diferentes marcos de registro (por ejemplo, java.util.logging, logback,
                Log4j). Ofrece una API genérica, lo que hace que el registro sea
                independiente de la implementación real. */
public class ControladorInicio
{
    @Autowired /* Para poder inyectar cualquier otra dependencia/objeto que sea administrado por el contenedor
                se utiliza la notación "@Autowired", muy similar a "Inject"*/
    private PersonaService personaService; /* Ahora se inyecta la capade servicio */

    /*  Con lo siguiente se está compartiendo información del MODELO hacia la VISTA */
    @GetMapping("/")
    public String inicio(Model model)/* Aquí se recibía el objero http servlet request y response, y utlizando
                                        uno de los alcances como "request", "session" o "application" se compartian
                                        atributos para desplegarlos en las paginas para desplegarlos en las paginas
                                        en los "jsp", en vez de todo eso se recibe un objeto llamado "model".
                                        Esta clase tiene la función de agregar información que se quiere compartir 
                                        con la vista (index.html)*/
    {
        var personas = personaService.listarPersonas(); /* Se cambia el método */

        log.info("Ejecutando controlador Spring MVC");
        /* return "html/index.html"; */
        model.addAttribute("personas", personas);
        return "index";

    }

    /* Path para el HTML del fomulario donde se agrega/modifica una persona */
    @GetMapping("/agregar")
    public String agregar(Persona persona)
    {
        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(Persona persona)
    {
        personaService.guardar(persona);
        return "redirect:/";
    }
    
    /* Para mapear este caso se utiliza "@GetMapping", para que 
    funcione, el id_debe ser exactamente igual al atributo de la
    clase que se quiere que se inicialice.
    
    "Persona persona": al definir un objeto de tipo "Persona", spring
    lo que hace es buscar el objeto, si existe entonces lo inyecta, y además 
    el parametro que está recibiendo lo va a asociar con el objeto tipo "persona",
    de manera automatica va a inicializar el objeto tipo "persona" con el valor
    de "id_persona".
    
    Model model: Se recibe la variable de modelo para poderla compartir
    para la siguiente petición

    Persona service: nos sirve para buscar el objeto, se manda a llamar
    el objeto "encontrarPersona", el cual el "objeto" persona ya tiene
    el id y por lo tanto ya lo puede encontrar en la base de datos.

    Finalmente se comparte el objeto "persona" utilizando la variable de "model" con "addAtribute"
    
    */
    @GetMapping("/editar/{id_persona}")
    public String editar(Persona persona, Model model)
    {
        persona = personaService.encontrarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";
    }

    /* Elimiación por forma de path */
    /* @GetMapping("/eliminar/{id_persona}")
    public String eliminar(Persona persona, Model model)
    {
        personaService.eliminar(persona);
        return "redirect:/";
    } */

    /* Elimiación por forma query param
       Los parámetros de consulta son parámetros adjuntos al final de una URL y se
       separan de la URL por un signo de interrogación (?). La sección antes del
       signo de interrogación es el parámetro de ruta, y la sección después del
       signo de interrogación es la consulta. El parámetro de ruta define la
       ubicación de los recursos, mientras que el parámetro de consulta define las
       operaciones de clasificación, paginación o filtro.
    */
    @GetMapping("/eliminar") 
    public String eliminar(Persona persona)
    {
        personaService.eliminar(persona);
        return "redirect:/";
    }

}
