package poly.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import poly.store.service.OrderService;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("checkout")
    public String checkout() {
        return "order/checkout";
    }

    @RequestMapping("list")
    public String list(Model model, HttpServletRequest request) {
        String username = request.getRemoteUser();
        model.addAttribute("orders", orderService.findByUsername(username));
        return "order/list";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable("id") Long id,
                         Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "order/detail";
    }
}
