package com.blogJPA.localtest.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
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
    @Column(name = "id_b")//Este parámetro es igual en el archivo MainController y MySQL
    private Integer identifier; //id_b INT AUTO_INCREMENT PRIMARY KEY

    @Column(name = "title_b",length = 60)
    private String title; //title_b VARCHAR(60)

    @Column(name = "content_b",nullable = false, columnDefinition = "TEXT")
    private String textline; //content_b TEXT not NULL,

    @Column(name = "category_b")// Anteriormente esta columna en Mysql Y java tenian el atributp NOT NULL / nullable = false
    //pero por impersistencia de datos se ha retirado.
    private String category; //category_b

    /*
    @ElementCollection
    @CollectionTable(name = "blog_tags", joinColumns = @JoinColumn(name = "blog_id"))
    @Column(name = "tag")
    private List<String> tags_b;
    ElementCollection busca una tabla más, una tabla llamada blog_tags con un campo id blog_id
    si quisieramos usarlo necesitamos este schema/esquema en MySQL
    CREATE TABLE blog (
      id_b INT AUTO_INCREMENT PRIMARY KEY,
      title_b VARCHAR(60),
      content_b TEXT NOT NULL,
      category_b VARCHAR(30),
      create_date DATE,
      update_date DATE
    );

    CREATE TABLE blog_tags (
      blog_id INT NOT NULL,
      tag VARCHAR(255),
      FOREIGN KEY (blog_id) REFERENCES blog(id_b)
    );
   */
    @Column(name = "tags_b", columnDefinition = "JSON")
    private String tags_b; //tags_b JSON

    //Las fechas insertardas sin @JsonProperty no se localizan en el puntero.
    //Ahí un error en el direccionamiento o en la compatobilidad de datos
    @JsonProperty("create_date")
    @Column(name = "create_date")
    private LocalDateTime create_date;    //create_date DATE

    @JsonProperty("update_date")
    @Column(name = "update_date")
    private LocalDateTime update_date; //update_date DATE

    //Los valores mínimos para una inserción controlada son:
    //content_b-->textline category_b-->category


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
    public void setTags_b(String tags_b) {
        this.tags_b = tags_b;
    }
    public void setCreate(LocalDateTime  create_date) {
        this.create_date = create_date;
    }
    public void setUpdate(LocalDateTime  update_date) {
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
    public String getTags_b() {
        return tags_b;
    }
    public LocalDateTime  getCreate() {
        return create_date;
    }
    public LocalDateTime  getUpdate() {
        return update_date;
    }




}


