package com.blogJPA.localtest.service;

import com.blogJPA.localtest.entity.Content;
import com.blogJPA.localtest.repository.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public void create (String content_bEx, String category_bEx) {

    Content blog = new Content();
   // blog.setIdentifier();
    blog.setTitle(null);
    blog.setTextline(content_bEx);
    blog.setCategory(category_bEx);
    blog.setTags_b(null);
    blog.setCreate(null);
    blog.setUpdate(null);
    blogRepository.save(blog);
    }



    public List<Content> readAll() {
        return List.copyOf((Collection<? extends Content>) blogRepository.findAll());
    }


    public Content readOne(Integer id_ex) {
        return blogRepository.findById(id_ex)
                .orElseThrow(() -> new EntityNotFoundException("Content not found with id: " + id_ex));
    }










}
