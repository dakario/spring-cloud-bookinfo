package com.bookinfo.rest;

import com.bookinfo.client.DetailsClient;
import com.bookinfo.client.RatingsClient;
import com.bookinfo.client.ReviewsClient;
import com.bookinfo.entity.Details;
import com.bookinfo.entity.Product;
import feign.RetryableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class ProductPageController {
    @Autowired
    private DetailsClient detailsClient;
    @Autowired
    private ReviewsClient reviewsClient;
    @Autowired
    private RatingsClient ratingsClient;

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
    //@HystrixCommand(fallbackMethod = "fallback_front", commandProperties = {
    //        @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    //})
    public String front(HttpServletRequest request, Model model) {
        Product product = getProducts().get(0);
        model.addAttribute("product", product);
        String user = (request.getSession() == null)? "" : (String) request.getSession().getAttribute("user");
        model.addAttribute("user", user);
        getDetails(product.getId(), model);
        getReviews(product.getId(), model);
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
        return detailsClient.getDetail(idProduct);
    }
    @RequestMapping(value = "/api/v1/products/{idProduct}/reviews", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getReviews(@PathVariable int idProduct){
        return reviewsClient.getReviews(idProduct);
    }
    @RequestMapping(value = "/api/v1/products/{idProduct}/ratings", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Object getRations(@PathVariable int idProduct){
        return ratingsClient.getRatings(idProduct);
    }
    private List<Product> getProducts(){
        Product product = Product.builder()
                .id(1)
                .title("The Comedy of Errors")
                .descriptionHtml("")
                .build();
        List<Product> products = new ArrayList<>();
        products.add(product);
        return products;
    }

    private String fallback_front(HttpServletRequest request, Model model) {
        return "productpage";
    }

    private void getDetails(int idProduct, Model model) {
        try {
            HashMap<String, String> header = new HashMap<>();
            header.put("user", "mor");
            model.addAttribute("details", detailsClient.getDetail(idProduct));
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
        catch (RetryableException ex){
            model.addAttribute("detailsStatus", 500);
            model.addAttribute("detailserror", "Sorry, product details are currently unavailable for this book.");
        }
        catch (Exception ex){
            model.addAttribute("detailsStatus", 500);
            model.addAttribute("detailserror", "Sorry, product details are currently unavailable for this book.");
        }
    }
    private void getReviews(int idProduct, Model model) {
        try {
            model.addAttribute("reviews", reviewsClient.getReviews(idProduct));
            model.addAttribute("reviewsStatus", 200);

        }
        catch (RestClientException ex){
            System.out.println(ex);
            if(ex instanceof HttpClientErrorException){
                model.addAttribute("reviewsStatus", ((HttpClientErrorException) ex).getStatusCode());
            }
            else {
                model.addAttribute("reviewsStatus", 500);
            }
            model.addAttribute("reviewserror", "Sorry, product reviews are currently unavailable for this book.");
        }
        catch (RetryableException ex){
            System.out.println(ex);
            model.addAttribute("reviewsStatus", 500);
            model.addAttribute("reviewserror", "Sorry, product reviews are currently unavailable for this book.");
        }
        catch (Exception ex){
            System.out.println(ex);
            model.addAttribute("reviewsStatus", 500);
            model.addAttribute("reviewserror", "Sorry, product reviews are currently unavailable for this book.");
        }
    }
}
