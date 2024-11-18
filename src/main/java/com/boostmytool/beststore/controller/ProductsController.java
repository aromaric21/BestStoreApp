package com.boostmytool.beststore.controller;

import com.boostmytool.beststore.models.Product;
import com.boostmytool.beststore.models.ProductDto;
import com.boostmytool.beststore.services.ProductsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository repo;

    @GetMapping({"", "/"})
    public String showProductList(Model model){
        List<Product> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreatePage(Model model){
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "products/createProduct";
    }

    @PostMapping("/create")
    public String createProduct(
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
    ){
        if (productDto.getImageFile().isEmpty()){
            result.addError(new FieldError("productDto", "imageFile", "The image file is required"));
        }

        if (result.hasErrors()){
            return "products/createProduct";
        }
        // Save the image file
        MultipartFile image = productDto.getImageFile();
        Date createdAt = new Date();
        String storageFileName =createdAt.getTime() + "_" + image.getOriginalFilename();

        try {
            String uploadDir = "public/images/";
            Path uploadPath = (Path) Paths.get(uploadDir);

            if (!Files.exists((java.nio.file.Path) uploadPath)){
                Files.createDirectories((java.nio.file.Path) uploadPath);
            }
            try (InputStream inputStream= image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setBrand(productDto.getBrand());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCreatedAt(createdAt);
        product.setImageFileName(storageFileName);

        repo.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(Model model, @RequestParam int id){

        try {

            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto", productDto);

        }catch (Exception ex){
            System.out.println("Exception:" + ex.getMessage());
            return "redirect:/products";
        }

        return  "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(Model model, @RequestParam int id,
                                @Valid @ModelAttribute ProductDto productDto,
                                BindingResult result){

        try {
            Product product =repo.findById(id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()){
                return "products/EditProduct";
            }

            if (!productDto.getImageFile().isEmpty()){
                // Delete old image
                String uploadDir = "public/images";
                Path oldImagePath = (Path) Paths.get(uploadDir + product.getImageFileName());
                try {
                    Files.delete((java.nio.file.Path) oldImagePath);
                }catch (Exception ex){
                    System.out.println("Exception:" + ex.getMessage());
                }

                // Save new image file
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();

                try(InputStream inputStream = image.getInputStream()) {

                    Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
                            StandardCopyOption.REPLACE_EXISTING);
                }
                product.setImageFileName(storageFileName);
            }

            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            repo.save(product);

        }catch (Exception ex){
            System.out.println("Exception:" + ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam int id
    ){
        try {
            Product product = repo.findById(id).get();

            // Delete product image
            Path imagePath = Paths.get("public/images" + product.getImageFileName());
            try {
                Files.delete(imagePath);
            }catch(Exception ex){
                System.out.println("Exception:" +ex.getMessage());
            }

            // Delete the product
            repo.delete(product);

        }catch (Exception ex){
            System.out.println("Exception:" + ex.getMessage());
        }
        return "redirect:/products";
    }
}
