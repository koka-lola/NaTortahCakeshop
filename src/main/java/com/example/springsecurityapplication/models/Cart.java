package com.example.springsecurityapplication.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "product_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "person_id")
    private int personId;


    @Column(name = "product_id")
    private int productId;
 //   @NotEmpty(message="Вес торта не может быть пустым")
    @Column(name = "qty") //вес торта в кг
    private float qty;
  //  @NotEmpty(message="Дата отдачи торта не может быть пустой")
    @Column(name = "date") //дата отдачи торта
    private String date;



    @Column(name = "comment") //Комментарии
    private String comment;

    @ManyToOne(optional = false)//связь с таблицей начинок
    private TortFilling tortFilling;


    public Cart(int person_id, int productId, TortFilling tortFilling) {
        this.personId = person_id;
        this.productId = productId;

        this.tortFilling= tortFilling;
    }

    public Cart() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

   /* public Cart(int id, int personId, int productId, float qty, Date date, String comment) {
        this.id = id;
        this.personId = personId;
        this.productId = productId;
        this.qty = qty;
        this.date = date;
        this.comment = comment;
    }*/

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }


    public TortFilling getTortFilling() {
        return tortFilling;
    }

    public void setTortFilling(TortFilling tortFilling) {
        this.tortFilling = tortFilling;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }
}
