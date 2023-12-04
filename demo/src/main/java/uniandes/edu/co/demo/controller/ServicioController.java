package uniandes.edu.co.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ServicioController {
    @RequestMapping("/administrador/servicios")
    public String index() {
        return "servicio";
    }
}
