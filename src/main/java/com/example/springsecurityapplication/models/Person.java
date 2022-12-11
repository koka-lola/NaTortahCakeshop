package com.example.springsecurityapplication.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Логин не может быть пустым")
    @Size(min = 5, max = 50, message = "Логин должен быть от 5 до 50 символов")
    @Column(name = "login")
    private String login;

    @NotEmpty(message = "Пароль не может быть пустым")
    @Column(name = "password")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Пароль должен содержать не менее 6 символов, хотя бы одну цифру, спец символ, букву в верхнем и нижнем регистре ")
    private String password;


    @NotEmpty(message = "E-mail не может быть пустым")
    @Column(name = "email")
    @Pattern(regexp = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$", message = "Задайте корректный адрес эл. почты")
    private String email;

    @NotEmpty(message = "Номер телефона не может быть пустым")
    @Column(name = "phone")
   // @Pattern(regexp = "^((\\\\+7|7|8)+([0-9]){10})$", message = "Номер телефона должен состоять из цифр и начинаться с 8/+7")
    private String phone;


    @NotEmpty(message = "Имя не может быть пустым")
    @Column(name = "firstname")
  //  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Пароль должен содержать не менее 6 символов, хотя бы одну цифру, спец символ, букву в верхнем и нижнем регистре ")
    private String firstname;

    @NotEmpty(message = "Дата рождения не может быть пустой")
    @Column(name = "birthdate")
    //  @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", message = "Пароль должен содержать не менее 6 символов, хотя бы одну цифру, спец символ, букву в верхнем и нижнем регистре ")
    private String birthdate;

  /*  @Column(name = "role")
    private String role;*/



    @ManyToMany()
    @JoinTable(name = "product_cart", joinColumns = @JoinColumn(name = "person_id"),inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @OneToMany(mappedBy = "person")
    private List<Orders> orderList;

    @ManyToOne(optional = false)
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person() {
    }

    public Person(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public Person(int id, String login, String password, String email, String phone, String firstname, String birthdate) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.firstname = firstname;
        this.birthdate = birthdate;

    }

}
