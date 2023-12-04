package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.demo.repository.ReservaRepository;

@Controller
public class RequerimientosController {

    @Autowired
    private ReservaRepository repository;


    @GetMapping("gerente/ocupacionhabitaciones")
    public String ocupacionhabitaciones(Model model) {
        model.addAttribute("resultados",repository.darIndiceOcupacion());
        return "ocupacionhabitaciones";
    }

    @GetMapping("gerente/habitaciones")
    public String habitaciones(Model model) {
        model.addAttribute("resultados",repository.calcularRecaudacionPorHabitacion());
        return "gerenteConsulta1";
    }

    @GetMapping("gerente/consumosUsuario")
    public String busqueda (Model model) {
        return "consumosUsuarioForm";
    }

    @PostMapping("gerente/consumosUsuario")
    public String consumoUsuario(Model model, @RequestParam String id, @RequestParam String fechaInicio, @RequestParam String fechaFin) {
        model.addAttribute("resultados",repository.obtenerConsumosPorUsuarioYFecha(Integer.parseInt(id), fechaInicio, fechaFin));
        return "consumoUsuario";
    }

    @GetMapping("gerente/informacionconsumo")
    public String informacionconsumo(Model model) {
        return "informacionconsumoForm";
    }

    @PostMapping("gerente/informacionconsumo")
    public String informacionconsumo(Model model, @RequestParam String fechaInicio, @RequestParam String fechaFin, @RequestParam String agrupar, @RequestParam String Criterio, @RequestParam String orden) {
        model.addAttribute("resultados",repository.obtenerConsumosPGrupoR4s(fechaInicio, fechaFin, agrupar, Criterio, Integer.parseInt(orden)));
        return "informacionconsumo";
    }
    
}

