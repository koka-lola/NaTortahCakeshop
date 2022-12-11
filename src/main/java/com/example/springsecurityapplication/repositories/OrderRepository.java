package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Orders;
import com.example.springsecurityapplication.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByPerson(Person person);
    Orders findById(int id); //поиск строки корзины по id

    @Query(value = "select * from orders where number LIKE %?1", nativeQuery = true)
    List<Orders> findByNumber(String number);

    @Query(value = "select * from orders", nativeQuery = true)
    List<Orders> findAll(String number);
}
