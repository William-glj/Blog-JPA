package com.blogJPA.localtest.service;

import com.blogJPA.localtest.entity.Content;
import com.blogJPA.localtest.repository.BlogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    //Método por parámetros
    public Content createByParam (String content_bEx, String category_bEx) {

    Content blog = new Content();
    blog.setTitle(null);
    blog.setTextline(content_bEx);
    blog.setCategory(category_bEx);
    blog.setTags_b(null);
    blog.setCreate(null);
    blog.setUpdate(null);

        blogRepository.save(blog);
    return blog;
    }

    public Content createByObject(Content content) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // Convertimos la lista de tags a JSON string
        String jsonTags = mapper.writeValueAsString(content.getTags_b());
        // Pasamos los datos
        content.setTags_b(jsonTags);


        blogRepository.save(content);
        return content;
    }




    public List<Content> readAll() {
        return List.copyOf((Collection<? extends Content>) blogRepository.findAll());
    }

    //Optional es cómo el gato de schrödingergato, puede ser un objeto Content o no, solo se conoce cuando falla el sistema.
    public Optional<Content> readOne(Integer id_ex) {
        return blogRepository.findById(id_ex);
    }










}
