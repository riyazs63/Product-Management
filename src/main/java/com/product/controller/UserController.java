package com.product.controller;

import com.product.Repository.UserRepository;
import com.product.entity.Product;
import com.product.entity.User;
import com.product.service.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductService productService;

    @ModelAttribute
    public User getUser(Principal p, Model m) {
        String email = p.getName();
        User user = userRepo.findByEmail(email);
        m.addAttribute("user", user);
        return user;
    }

    @ModelAttribute
    public User getUser1(Principal principal) {
        String email = principal.getName();
        User user = userRepo.findByEmail(email);
        return user;
    }

    @GetMapping("/addProduct")
    public String addProduct() {
        return "addProduct";
    }

    /*@GetMapping("/viewProduct/{page}")
    public String viewProduct(@PathVariable("page") Integer page,Model m, Principal p) {
        User user = getUser(p, m);

        Pageable pageable = PageRequest.of(page, 5);
        Page<Product> products = productService.getProductsByUser(user,pageable);
        m.addAttribute("productsList", products);
        m.addAttribute("currentPage",page);
        m.addAttribute("totalPages",products.getTotalPages());

        return "viewProduct";

    }*/
    @GetMapping("/viewProduct")
    public String viewProduct(Model m, Principal p) {
        User user = getUser(p, m);
        List<Product> products = productService.getProductsByUser(user);
        m.addAttribute("productsList", products);
        return "viewProduct";

    }

    /*@GetMapping("/viewProduct/{page}")
    public String viewProduct(@PathVariable("page") Integer page, Model m, Principal p) {
        User user = getUser(p, m);

        int pageSize = 5;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Product> products = productService.getProductsByUser(user, pageable);
        m.addAttribute("productsList", products);
        m.addAttribute("currentPage", page);
        m.addAttribute("totalPages", products.getTotalPages());

        return "viewProduct";
    }*/


    @GetMapping("/editProduct/{id}")
    public String editProduct(@PathVariable int id, Model m) {
        Product productsById = productService.getProductsById(id);
        m.addAttribute("productsById", productsById);
        return "editProduct";
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product, HttpSession session, Principal p, Model m) {


        product.setUser(getUser(p, m));

        Product saveProduct = productService.saveProduct(product);
        if (saveProduct != null) {
            session.setAttribute("msg", "Product Saved successfully");
        } else {
            session.setAttribute("msg", "Something wrong on server");
        }
        return "redirect:/user/addProduct";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product product, HttpSession session, Principal p, Model m) {


        product.setUser(getUser(p, m));

        Product saveProduct = productService.saveProduct(product);
        if (saveProduct != null) {
            session.setAttribute("msg", "Product updated successfully");
        } else {
            session.setAttribute("msg", "Something wrong on server");
        }
        return "redirect:/user/viewProduct";
    }


    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session) {
        boolean deleteProduct = productService.deleteProduct(id);
        if (deleteProduct) {
            session.setAttribute("msg", "Product Deleted successfully");
        } else {
            session.setAttribute("msg", "Something wrong on server");
        }

        return "redirect:/user/viewProduct";
    }

  /*  @GetMapping("/searchProduct")
    public String searchProduct(@RequestParam("keyword") String keyword, Model m, Principal p) {
        User user = getUser(p, m);
        List<Product> searchResults = productService.searchProductsByKeyword(user, keyword);
        m.addAttribute("productsList", searchResults);
        return "viewProduct";
    }*/

}
