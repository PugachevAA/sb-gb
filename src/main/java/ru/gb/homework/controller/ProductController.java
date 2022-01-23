package ru.gb.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.homework.entity.Product;
import ru.gb.homework.service.ProductService;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //метод создания формы
    @GetMapping("/create")
    public String showSimpleForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "create-product";
    }


    //метод обработки формы и созранения в БД
    @PostMapping("/create")
    public String processForm(Product product){
        if (product.getId() == null) {
            productService.save(product);
        } else {
            productService.edit(product);
        }
        return "redirect:/product/all";
    }

    //метод вывода одного сообщения в браузер message/3?random=true|false
    @GetMapping("/{id}")
    public String getProductById(Model model, @PathVariable Integer id)  {
        Product product = null;
        product = productService.findById(id);
        model.addAttribute("product", product);
        return "product";
    }

    //метод вывода списка сообщений в браузер
    @GetMapping("/all")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "product-list";
    }
    //метод удаления сообщения из хранилища
    @GetMapping("/delete")
    public String deleteById(@RequestParam Integer id) {
        productService.deleteById(id);
        return "redirect:/product/all";
    }

    //метод редактирования
    @GetMapping("/edit")
    public String editById(Model model, @RequestParam Integer id) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "create-product";
    }
}
