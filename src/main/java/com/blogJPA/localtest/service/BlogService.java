package com.blogJPA.localtest.service;

import com.blogJPA.localtest.classBox.BlogDTO;
import com.blogJPA.localtest.entity.BlogClass;
import com.blogJPA.localtest.repository.BlogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    //Método por parámetros
    public BlogClass createByParam(String content_bEx, String category_bEx) {

        BlogClass blog = new BlogClass();
        blog.setTitle(null);
        blog.setTextline(content_bEx);
        blog.setCategory(category_bEx);
        blog.setTags_b(null);
        blog.setCreate(null);
        blog.setUpdate(null);

        blogRepository.save(blog);
        return blog;
    }

    //Objeto entero
    public BlogClass createByObject(BlogClass content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // Convertimos la lista de tags a JSON string
        String jsonTags = mapper.writeValueAsString(content.getTags_b());
        // Pasamos los datos
        content.setTags_b(jsonTags);


        blogRepository.save(content);
        return content;
    }


    public List<BlogClass> readAll() {
        return List.copyOf((Collection<? extends BlogClass>) blogRepository.findAll());
    }

    //Optional es cómo el gato de schrödingergato, puede ser un objeto BloggClass o no, solo se conoce cuando falla el sistema.
    public Optional<BlogClass> readOne(Integer id_ex) {
        return blogRepository.findById(id_ex);
    }
    //Parámetros
    public ResponseEntity<BlogClass> update(Integer id_ex, String title_b, String content_b, String category_b, String tags_b) {

       return blogRepository.findById(id_ex).map(newBlog ->{
           //Un posible error es que al introducir los datos en la url/endpoint se reciban las llaves -> {}
           newBlog.setTitle(title_b);
           newBlog.setTextline(content_b);
           newBlog.setCategory(category_b);
           newBlog.setTags_b(tags_b);
           BlogClass upateCase = blogRepository.save(newBlog);
            return ResponseEntity.ok(upateCase);

        }).orElseGet(() -> ResponseEntity.notFound().build());


    }

    //Objeto entero
    public ResponseEntity<BlogClass> updateByObj(Integer id, BlogDTO datosActualizados) {
        return blogRepository.findById(id).map(blog -> {
            blog.setTitle(datosActualizados.title());
            blog.setTextline(datosActualizados.textline());
            blog.setCategory(datosActualizados.category());
            blog.setTags_b(datosActualizados.tags_b());
            BlogClass actualizado = blogRepository.save(blog);
            return ResponseEntity.ok(actualizado);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }









}
