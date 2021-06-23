package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ShoppingCartController {

    @PostMapping("/addtocart")
    public ModelAndView add2Cart(HttpSession session, @ModelAttribute("product") Product product) {
        ShoppingCart shoppingList;
        System.out.println(product.getProductNumber());
        Map<String, Object> params = new HashMap<>();
        if (product != null) {
            //get the productList from the session
            shoppingList = (ShoppingCart) session.getAttribute("shoppingList");
            //if there is no productList in the session, create one.
            if (shoppingList == null) {
                shoppingList = new ShoppingCart();
                session.setAttribute("shoppingList", shoppingList);
            }
            //add the car to the productList
//            product.setQuantity();
            shoppingList.addProduct(product);
            params.put("shoppingList", shoppingList.getProducts());
        }
        return new ModelAndView("showcart", params);
    }

    @PostMapping("/removeproductCart")
    public ModelAndView removeproductCart(@RequestParam(value = "productnumberCart") String productnumberCart, HttpSession session) {
        ShoppingCart shoppingList;
        Map<String, Object> params = new HashMap<>();
        if (productnumberCart != null) {
            //get the productList from the session
            shoppingList = (ShoppingCart) session.getAttribute("shoppingList");
            //if there is no productList in the session, create one.
            if (shoppingList == null) {
                shoppingList = new ShoppingCart();
                session.setAttribute("shoppingList", shoppingList);
            }
            //add the car to the productList
            shoppingList.removeProduct(productnumberCart);
//            shoppingList.remove(productNumber);
            params.put("shoppingList", shoppingList.getProducts());
        }
        return new ModelAndView("showcart", params);
    }


}
