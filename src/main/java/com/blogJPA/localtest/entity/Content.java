package com.blogJPA.localtest.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

//Los elementos/names deben de coincidir con los atributos reales de la base de datos.
@Entity
@Table(name = "blog")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_b")
    private Integer identifier; //id_b INT AUTO_INCREMENT PRIMARY KEY

    @Column(name = "title_b",length = 60)
    private String title; //title_b VARCHAR(60)

    @Column(name = "content_b",nullable = false, columnDefinition = "TEXT")
    private String textline; //content_b TEXT not NULL,

    @Column(name = "category_b",nullable = false,length = 30)
    private String category; //category_b VARCHAR(30) not NULL

    @ElementCollection
    @CollectionTable(name = "blog_tags",joinColumns = @JoinColumn(name = "blog_id"))
    @Column(name = "tags_b")
    private List<String> tags_b; //tags_b JSON

    private Date create_date; //create_date DATE
    private Date update_date; //update_date DATE


    public void setIdentifier(Integer identifier) {
        this.identifier = identifier;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setTextline(String textline) {
        this.textline = textline;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setTags_b(List<String> tags_b) {
        this.tags_b = tags_b;
    }
    public void setCreate(Date create) {
        this.create_date = create_date;
    }
    public void setUpdate(Date update_date) {
        this.update_date = update_date;
    }
    public Integer getIdentifier() {
        return identifier;
    }
    public String getTitle() {
        return title;
    }
    public String getTextline() {
        return textline;
    }
    public String getCategory() {
        return category;
    }
    public List<String> getTags_b() {
        return tags_b;
    }
    public Date getCreate() {
        return create_date;
    }
    public Date getUpdate() {
        return update_date;
    }

    public String toString(){

        return "Identificador: " + getIdentifier() +
                " titulo: " +getTitle() +
                " contenido:" + getTextline() +
                " categoria: " + getCategory() +
                " tags: " + getTags_b().toString() +
                " fecha de creación: " + getCreate() +
                " fecha de actualización:" + getUpdate();

    }



}


