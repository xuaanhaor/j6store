package poly.store.rest.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import poly.store.service.RoleService;
import poly.store.entity.Role;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/rest/roles")
public class RoleRestController {
    @Autowired
    RoleService roleService;

    @GetMapping
    public List<Role> getAll(){
        return roleService.findAll();
    }
}
