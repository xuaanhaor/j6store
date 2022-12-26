package poly.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import poly.store.entity.Product;
import poly.store.service.ProductService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("list")
    public String list(Model model, @RequestParam("cid") Optional<String> cid) {
        if (cid.isPresent()) {
            List<Product> list = productService.findByCategoryId(cid.get());
            model.addAttribute("items",list);
        } else {
            List<Product> list = productService.findAll();
            model.addAttribute("items", list);
        }

        return "product/list";
    }

    @RequestMapping("detail/{id}")
    public String detail(Model model,@PathVariable Integer id) {
        Product item = productService.findById(id);
        model.addAttribute("item",item);
        return "product/detail";
    }
}
