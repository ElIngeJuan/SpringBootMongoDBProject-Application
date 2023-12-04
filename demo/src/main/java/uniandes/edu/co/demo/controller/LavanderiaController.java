package uniandes.edu.co.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.repository.ServicioRepository;

@Controller
public class LavanderiaController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/servicioropa")
    public String lavanderia(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Lavanderia"));
        return "ServicioRopa";
    }

    @GetMapping("/administrador/servicios/servicioropa/new")
    public String lavanderiaNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "ServicioRopaNuevo";
    }

    @PostMapping("/administrador/servicios/servicioropa/new/save")
    public String lavanderiaSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/servicioropa";
    }

    @GetMapping("/administrador/servicios/servicioropa/{id}/edit")
    public String lavanderiaEdit( Model model, @PathVariable("id") String id) {
        model.addAttribute("servicio", repository.findById(id).get());
        return "ServicioRopaEditar";
    }
    
    @PostMapping("/administrador/servicios/servicioropa/{id}/edit/save")
    public String lavanderiaUpdate(Servicio servicio, @PathVariable("id") String id) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/servicioropa";
    }

    @GetMapping("/administrador/servicios/servicioropa/{id}/delete")
    public String lavanderiaDelete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/servicioropa";
    }


}
