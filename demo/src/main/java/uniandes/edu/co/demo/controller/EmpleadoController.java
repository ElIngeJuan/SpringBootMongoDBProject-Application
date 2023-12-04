package uniandes.edu.co.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpleadoController {
    @GetMapping("/empleado")
    public String cliente() {
        return "empleado";
    }
}
