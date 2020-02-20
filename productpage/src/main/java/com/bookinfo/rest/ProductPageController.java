package com.bookinfo.rest;

import com.bookinfo.client.DetailsService; 
import com.bookinfo.client.ReviewsService;
import com.bookinfo.entity.Details;
import com.bookinfo.entity.Product;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductPageController {
    @Autowired
    private DetailsService detailsClient;
    @Autowired
    private ReviewsService reviewsClient;

    @GetMapping("/")
    public String getIndex(Model model) {
        model.addAttribute("name", "hello world");
        return "index";
    }
    @GetMapping("/health")
    public String health(){
        return "Product page is healthy";
    }
    @GetMapping("/login")
    public String login(@RequestParam(name = "username", required = true) String username,
                        HttpServletRequest request){
        String user = username;
        request.getSession().setAttribute("user", user);
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/productpage")
    @HystrixCommand(fallbackMethod = "fallback_front", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })
    public String front(HttpServletRequest request, Model model) {
        Product product = getProducts().get(0);
        model.addAttribute("product", product);
        String user = (request.getSession() == null)? "" : (String) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        getDetails(product.getId(), model);
        getReviews(product.getId(), model);
        return "productpage";
    }
    
    private String fallback_front(HttpServletRequest request, Model model) {
    	System.out.println("frontfallback called...");
        model.addAttribute("reviewserror", "Sorry, product reviews are currently unavailable for this book.");
    	return "productpage";
    }

    @RequestMapping(value = "/api/v1/products", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Product> getAllProducts(){
        return getProducts();
    }
    @RequestMapping(value = "/api/v1/products/{idProduct}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Details getProduct(@PathVariable int idProduct){
        return detailsClient.getDetailByIdProduct(idProduct);
    }
    @RequestMapping(value = "/api/v1/products/{idProduct}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getReviews(@PathVariable int idProduct){
        return reviewsClient.getReviewsByIdProduct(idProduct);
    }
    
    private List<Product> getProducts(){
        Product product = Product.builder()
                .id(1)
                .title("The Comedy of Errors")
                .descriptionHtml("");
                //.build();
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }


    private void getDetails(int idProduct, Model model) {
        try {
            model.addAttribute("details", detailsClient.getDetailByIdProduct(idProduct));
            model.addAttribute("detailsStatus", 200);
        }
        catch (RestClientException ex){
            if(ex instanceof HttpClientErrorException){
                model.addAttribute("detailsStatus", ((HttpClientErrorException) ex).getStatusCode());
            }
            else {
                model.addAttribute("detailsStatus", 500);
            }
            model.addAttribute("detailserror", "Sorry, product details are currently unavailable for this book.");
        }
        catch (Exception ex){
            model.addAttribute("detailsStatus", 500);
            model.addAttribute("detailserror", "Sorry, product details are currently unavailable for this book.");
        }
    }
    private void getReviews(int idProduct, Model model) {
    	model.addAttribute("reviews", reviewsClient.getReviewsByIdProduct(idProduct));
    	model.addAttribute("reviewsStatus", 200);
    }
}
