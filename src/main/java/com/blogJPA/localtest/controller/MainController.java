package com.blogJPA.localtest.controller;

import com.blogJPA.localtest.entity.Content;
import com.blogJPA.localtest.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/content", params = "id_b", method = RequestMethod.GET)
    public Content getContent(@RequestParam Integer id_b) {
        return blogService.readOne(id_b);
    }

    @RequestMapping(value = "/content/All", method = RequestMethod.GET)
    public List<Content> getAllContent() {
        return List.copyOf(blogService.readAll());
    }


}
