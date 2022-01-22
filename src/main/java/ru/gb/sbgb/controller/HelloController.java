package ru.gb.sbgb.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.gb.sbgb.config.ProductProps;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final ProductProps productProps;

    @ResponseBody
    @GetMapping
    public String helloMessage() {
        return "Hello Spring Boot";
    }

    @GetMapping("/hello")
    public String helloMessageJsp(Model model) {
        String message;
        if (productProps != null && !CollectionUtils.isEmpty(productProps.getProducts())) {
            message = "size: " + productProps.getProducts().size();
        } else {
            message = "Empty list of products";
        }
        model.addAttribute("msg", message);
        return "hello";
    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleNPE(NullPointerException e) {
        return "So sad, but... fail " + e.getMessage();
    }
}
