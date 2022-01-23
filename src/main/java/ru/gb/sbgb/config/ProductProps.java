package ru.gb.sbgb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.gb.sbgb.entity.Product;

import java.util.List;

@Configuration
@ConfigurationProperties("shop.properties")
@Getter
@Setter
public class ProductProps {
    private List<Product> products;
}
