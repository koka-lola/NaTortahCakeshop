package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Image;
import com.example.springsecurityapplication.models.Orders;
import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.CategoryRepository;
import com.example.springsecurityapplication.repositories.OrderRepository;
import com.example.springsecurityapplication.repositories.RoleRepository;
import com.example.springsecurityapplication.repositories.TortFillingRepository;
import com.example.springsecurityapplication.security.PersonDetails;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.util.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
//@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController {

    @Value("${upload.path}")
    private String uploadPath;

    private final ProductValidator productValidator;
    private final ProductService productService;
    private final PersonService personService;

    private final CategoryRepository categoryRepository;
    private final RoleRepository roleRepository;
    private final OrderRepository orderRepository;
    private final TortFillingRepository tortFillingRepository;
   // private final OrderService orderService;


    @Autowired
    public AdminController(ProductValidator productValidator, ProductService productService, PersonService personService, CategoryRepository categoryRepository, RoleRepository roleRepository, OrderRepository orderRepository, TortFillingRepository tortFillingRepository) {
        this.productValidator = productValidator;
        this.productService = productService;
        this.personService = personService;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
        this.orderRepository = orderRepository;
        this.tortFillingRepository = tortFillingRepository;
    }

    //    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('')")
//@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('')")

    // Метод по отображению главной страницы администратора с выводом товаров
    @GetMapping()
    public String admin(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        //String role = personDetails.getPerson().getRole();
        int role = personDetails.getPerson().getRole().getId();

      //  if(role.equals("ROLE_USER")){
        if(role == 3){
            return "redirect:/index";
        }
        model.addAttribute("products", productService.getAllProduct());
        model.addAttribute("persons", personService.getAllPersons());
        return "admin/admin";
    }

    // Метод по отображению формы добавление
    @GetMapping("/product/add")
    public String addProduct(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("category", categoryRepository.findAll());
//        System.out.println(categoryRepository.findAll().size());
        return "product/addProduct";
    }
    // Метод по отображению формы со списком пользователей
    @GetMapping("/product/usersList")
    public String showUser(Model model){
       // model.addAttribute("person", new Person());
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("role", roleRepository.findAll());
//        System.out.println(categoryRepository.findAll().size());
        return "admin/usersList";
    }

    // Метод по добавлению объекта с формы в таблицу product
    @PostMapping("/product/add")
    public String addProduct(@ModelAttribute("product") @Valid Product product, BindingResult bindingResult, @RequestParam("file_one") MultipartFile file_one, @RequestParam("file_two") MultipartFile file_two, @RequestParam("file_three") MultipartFile file_three, @RequestParam("file_four") MultipartFile file_four, @RequestParam("file_five") MultipartFile file_five) throws IOException {

        productValidator.validate(product, bindingResult);
        if(bindingResult.hasErrors()){
            return "product/addProduct";
        }
        // Проверка на пустоту файла
        if(file_one != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_one.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_one.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_two != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_two.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_two.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_three != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_three.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_three.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_four != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_four.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_four.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        // Проверка на пустоту файла
        if(file_five != null){
            // Дирректория по сохранению файла
            File uploadDir = new File(uploadPath);
            // Если данной дирректории по пути не сущетсвует
            if(!uploadDir.exists()){
                // Создаем данную дирректорию
                uploadDir.mkdir();
            }
            // Создаем уникальное имя файла
            // UUID представляет неищменный универсальный уникальный идентификатор
            String uuidFile = UUID.randomUUID().toString();
            // file_one.getOriginalFilename() - наименование файла с формы
            String resultFileName = uuidFile + "." + file_five.getOriginalFilename();
            // Загружаем файл по указаннопу пути
            file_five.transferTo(new File(uploadPath + "/" + resultFileName));
            Image image = new Image();
            image.setProduct(product);
            image.setFileName(resultFileName);
            product.addImageProduct(image);
        }

        productService.saveProduct(product);
        return "redirect:/admin";
    }

    // Метод по удалению товара по id
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.deleteProduct(id);
        return "redirect:/admin";
    }

    // Метод по получению товара по id и отображение шаблона редактирования
    @GetMapping("/product/edit/{id}")
    public String editProduct(@PathVariable("id") int id, Model model){
        model.addAttribute("editProduct", productService.getProductId(id));
        model.addAttribute("category", categoryRepository.findAll());
        return "product/editProduct";
    }

    @PostMapping("/product/edit/{id}")
    public String editProduct(@ModelAttribute("editProduct") Product product, @PathVariable("id") int id){
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }
    // Метод по получению пользователя по id и отображение шаблона редактирования
    @GetMapping("/product/usersList/editUser/{id}")
    public String editUser(@PathVariable("id") int id, Model model){
        model.addAttribute("editUser", personService.getPersonId(id));
        model.addAttribute("role", roleRepository.findAll());
      return "admin/editUser";
       // return "admin/product/usersList/editUser";
    }
    //Форма редактирования пользователей
    @PostMapping("/product/usersList/editUser/{id}")
    public String editUser(@ModelAttribute("editUser") Person person, @PathVariable("id") int id){
       // System.out.printf("%1",person.getLogin());
        personService.updatePerson(id, person);
       // return "admin/editUser";
        return "redirect:/admin/product/usersList";
    }
    // Метод по удалению пользователя по id
    @GetMapping("/product/usersList/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id){
        personService.deletePerson(id);
        return "redirect:/admin/product/usersList";
    }

    //----------------------------ЗАКАЗЫ-----------------------------
    // Метод по отображению формы со списком заказов
    @GetMapping("/product/orderList")
    public String showOrders(Model model){
        // model.addAttribute("person", new Person());
        float summ = 0;
        List<Orders> ordersList = orderRepository.findAll();
        for (Orders order: ordersList){
            summ += order.getPrice() * order.getQty();
        }
        model.addAttribute("persons", personService.getAllPersons());
        model.addAttribute("orders", orderRepository.findAll());
        model.addAttribute("summ", summ);
        return "admin/orderList";
    }
    // Метод по получению заказа по id и отображение шаблона редактирования
   @GetMapping("/product/orderList/editOrder/{id}")
    public String editOrder(@PathVariable("id") int id, Model model){
       // model.addAttribute("editUser", personService.getPersonId(id));
       model.addAttribute("editOrder", orderRepository.findById(id));
       model.addAttribute("tortFilling", tortFillingRepository.findAll());
       return "admin/editOrder";
        // return "admin/product/usersList/editUser";
    }
    //Форма редактирования заказов
    @PostMapping("/product/orderList/editOrder/{id}")
    public String editOrder(@ModelAttribute("editOrder") Orders order, @PathVariable("id") int id){
        // System.out.printf("%1",person.getLogin());
        orderRepository.save(order);
        // return "admin/editUser";
        return "redirect:/admin/product/orderList";
    }
    // Метод по удалению заказа по id
    @GetMapping("/product/orderList/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") int id){
        orderRepository.deleteById(id);
        return "redirect:/admin/product/orderList";
    }

    //Поиск по номеру заказа
    @PostMapping("/orderList/search")
    public String orderListSearch(@RequestParam("search") String search, Model model){
        System.out.println(search);
        // Если условие поиска не пустое
        if(!search.isEmpty()) {
            model.addAttribute("search_order", orderRepository.findByNumber(search.toLowerCase()));
        } else  model.addAttribute("search_order", orderRepository.findAll(search.toLowerCase()));
        model.addAttribute("value_search", search);
     //   model.addAttribute("orders", orderRepository.findAll());
        return "/admin/orderList";
    }


}
