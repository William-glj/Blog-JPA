package com.blogJPA.localtest.controller;

import com.blogJPA.localtest.classBox.BlogDTO;
import com.blogJPA.localtest.entity.BlogClass;
import com.blogJPA.localtest.service.BlogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private BlogService blogService;

    //Ruta   /api/content
    //Ruta + parámetro /api/content?id_b=x
    //Leer un solo contenido en específico por ID .
    @RequestMapping(value = "/content", params = "id_b", method = RequestMethod.GET)
    public ResponseEntity<BlogClass> getContent(@RequestParam Integer id_b) {

        return blogService.readOne(id_b)
                .map(ResponseEntity::ok)//200 Http bien
                .orElse(ResponseEntity.notFound().build()); //400 Http bien
    }


    //Ruta /api/content/all
    //Leer toda la lista de contenidos.
    @GetMapping("/content/all")
    public ResponseEntity<List<BlogClass>> getContentAll (){

        return ResponseEntity.ok(blogService.readAll());//200 Httpp

    }

    //Ruta  /api/content/insertOne
    //Ruta + Parámetros /api/content/insertOne?content_b=xxxxx&category_b=xxxxxxx
    //Creación por parámetros.
    //Funciona correctamente
    @RequestMapping(value = "/content/insertOne", params = {"content_b", "category_b"}, method = RequestMethod.POST)
    public ResponseEntity<BlogClass> setContentParam(
            @RequestParam("content_b") String content_bEx,
            @RequestParam("category_b") String category_bEx
    ) {
        //System.out.println(category_bEx);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(blogService.createByParam(content_bEx, category_bEx));
    }


    //Ruta /api/content/insert
    //Creación por Entidad.
    @PostMapping("/content/insertTwo")
    public ResponseEntity<BlogClass> setContentEntity(@RequestBody BlogClass content) throws JsonProcessingException {
        BlogClass savedContent = blogService.createByObject(content);

        //System.out.println(savedContent.getCategory());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedContent);
    }

    //Pruebas de insercciones realizadas y pasados
    /*
       {
          "title": "Introducción a Spring Boot",
          "textline": "Spring Boot simplifica el desarrollo de aplicaciones Java empresariales.",
          "category": "Desarrollo",
          "tags_b": "[Spring, Java, Backend]",
          "create_date": "2025-08-25T12:30:00",
          "update_date": "2025-08-25T12:30:00"
        }


        {
          "title": "Introducción a Spring Boot",
          "textline": "Spring Boot simplifica el desarrollo de aplicaciones Java empresariales.",
          "category": "Desarrollo",
          "tags_b": "[\"Spring\", \"Java\", \"Backend\"]",
          "create_date": "2025-08-25T12:30:00",
          "update_date": "2025-08-25T12:30:00"
        }

     */

    //  Ruta /api/content/update/{id}/{title}/{content}/{category}/{tags}
    //  Actualización mediante parámtetros.
    //  Caso de prueba --> localhost:8080/api/content/update/18/Update/Update/Update/Update
    @PutMapping("/content/update/{id}/{title}/{content}/{category}/{tags}")
    public ResponseEntity<BlogClass> updateContent(@PathVariable Integer id,
                                                   @PathVariable String title,
                                                   @PathVariable String content,
                                                   @PathVariable String category,
                                                   @PathVariable String tags) {
        return blogService.update(id, title, content, category, tags);
    }


    //  Ruta /api/content/update/{id}/{title}/{content}/{category}/{tags}
    //  Actualización mediante objeto.
    /*  Datos de objeto {







       }
    */

    @PutMapping("/content/update/{id}")
    public ResponseEntity<BlogClass> updateContentByObject(@PathVariable Integer id,
                                                           @RequestBody  BlogDTO blog) {
        return blogService.updateByObj(id, blog );
    }








}
