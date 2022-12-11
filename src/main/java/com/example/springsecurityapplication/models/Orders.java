package com.example.springsecurityapplication.models;

import com.example.springsecurityapplication.enumm.Status;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String number;

    private float qty;

    private float price;

    private LocalDateTime dateTime;

    private Status status;
  //  @NotEmpty(message="Дата отдачи торта не может быть пустой")
    @Column(name = "date") //дата отдачи торта
    private String date;

    @Column(name = "comment") //Комментарии
    private String comment;


    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private Person person;

    @ManyToOne(optional = false)
    private TortFilling tortFilling;

    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public float getCount() {
        return qty;
    }

    public void setCount(int count) {
        this.qty = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public float getQty() {
        return qty;
    }

    public String getDate() {
        return date;
    }

    public String getComment() {
        return comment;
    }

    public TortFilling getTortFilling() {
        return tortFilling;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setTortFilling(TortFilling tortFilling) {
        this.tortFilling = tortFilling;
    }

    public Orders(String number, float qty, float price, Status status, Product product, Person person, String comment, String date, TortFilling tortFilling) {
        this.number = number;
        this.qty = qty;
        this.price = price;
        this.status = status;
        this.product = product;
        this.person = person;
        this.date = date;
        this.comment = comment;
        this.tortFilling = tortFilling;
    }

    public Orders(){

    }
}
