package org.fungover.webapi.controllers;


import org.fungover.webapi.Text;
import org.fungover.webapi.entities.Product;
import org.fungover.webapi.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



import javax.annotation.security.RolesAllowed;


@RestController
public class HelloController {


    ProductRepository productRepository;

    public HelloController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @RequestMapping("/greeting")
    // namnet behöver inte heta samma /greeting och metoden greeting
    public Text greeting(){

        Text text = new Text("dasda");


        return text;
    }


    @RequestMapping("/bye")
    public  String goodbye(){

        return "Bye!!";
    }

    @RolesAllowed("ROLE_USER")
    @GetMapping("/products")
    public Iterable<Product> products(){
        return productRepository.findAll();
    }




    @PostMapping("/products")
    public ResponseEntity<Product> create(@RequestBody Product product){
        //Validate product
        if (product.getPrice()<0)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

      return new ResponseEntity<>( productRepository.save(product), HttpStatus.CREATED);
    }

    // behöver inget svar tibax
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        // tveksam att deta behövs
        if (!productRepository.findById(id).isPresent())
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        productRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // RPC-CALL , remote procedure call.
    @GetMapping("add/{tal1}/{tal2}")
    public int add(@PathVariable int tal1,@PathVariable int tal2){
        return  tal1 +tal2;
    }


    






























}
