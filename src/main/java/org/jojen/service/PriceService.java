package org.jojen.service;

import org.jojen.model.Product;
import org.jojen.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Service
public class PriceService {

    @Autowired
    ProductRepository productRepository;

    public String validate(Product configuratedProduct) {
        try{
            Optional<Product> p = productRepository.findById(configuratedProduct.getId());
            Product product = p.get();

            Double length = configuratedProduct.getLength().getSelectedValue();
            Double width = configuratedProduct.getWidth().getSelectedValue();
            Double height = configuratedProduct.getHeight().getSelectedValue();

            Double cube = width * length * height;

            Double price = product.getFixcosts();
            price = price + (cube * product.getWoodbaseprice());

            return new BigDecimal( price ).setScale( 0 , RoundingMode.CEILING ) +" €";
        }catch (Exception e){
            return "auf Anfrage";
        }
    }
}
