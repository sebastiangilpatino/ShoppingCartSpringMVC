package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @GetMapping("/products")
    public ModelAndView init(HttpSession session) {
        //get the productList from the session
        Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
        //if there is no productList in the session, create one.
        if (productList == null) {
            productList = new HashMap<String, Product>();
            session.setAttribute("productList", productList);
        }
        Map<String, Object> params = new HashMap<>();
        params.put("productList", productList.values());
        return new ModelAndView("products", params);
    }

    @GetMapping("/addproduct")
    public ModelAndView addproduct() {
        Map<String, Object> params = new HashMap<>();
        params.put("product", new Product());
        return new ModelAndView("addproduct", params);
    }

    @PostMapping("/add")
    public ModelAndView add(HttpSession session, @ModelAttribute("product") Product product,
                            @Valid Product productV, BindingResult bindingResult) {
        Map<String, Object> params = new HashMap<>();
        if (product != null) {
            //get the productList from the session
            Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
            //if there is no productList in the session, create one.
            if (productList == null) {
                productList = new HashMap<String, Product>();
                session.setAttribute("productList", productList);
            }

            if (bindingResult.hasErrors()) {
                return new ModelAndView("addproduct", params);
            }

            //add the car to the productList
            productList.put(product.getProductNumber(), product);
            params.put("productList", productList.values());


        }
        return new ModelAndView("redirect:products", params);
    }

    @PostMapping("/removeproduct")
    public ModelAndView removeproduct(@RequestParam(value = "productnumber") String productNumber, HttpSession session) {
        Map<String, Object> params = new HashMap<>();
        if (productNumber != null) {
            //get the productList from the session
            Map<String, Product> productList = (Map<String, Product>) session.getAttribute("productList");
            //if there is no productList in the session, create one.
            if (productList == null) {
                productList = new HashMap<String, Product>();
                session.setAttribute("productList", productList);
            }
            //add the car to the productList
            productList.remove(productNumber);
            params.put("productList", productList.values());
        }
        return new ModelAndView("redirect:products", params);
    }

}
