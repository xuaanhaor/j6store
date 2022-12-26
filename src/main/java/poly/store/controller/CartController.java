package poly.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart")
public class CartController {


    @RequestMapping("view")
    public String view() {
        return "cart/view";
    }
}
