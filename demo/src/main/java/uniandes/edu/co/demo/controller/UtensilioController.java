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
public class UtensilioController {
   
    @Autowired
    private ServicioRepository repository;
    
    @GetMapping("/administrador/servicios/utensilio")
    public String utensilio(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Utensilios"));
        return "utensilio";
    }

    @GetMapping("/administrador/servicios/utensilio/new")
    public String newUtensilio(Model model) {
        model.addAttribute("utensilio", new Servicio());
        return "utensilioNuevo";
    }

    @PostMapping("/administrador/servicios/utensilio/new/save")
    public String saveUtensilio(Servicio servicio) {
        servicio.setTipo("Utensilios");
        repository.save(servicio);
        return "redirect:/administrador/servicios/utensilio";
    }

    @GetMapping("/administrador/servicios/utensilio/{id}/edit")
    public String editUtensilio(Model model, @PathVariable String id) {
        model.addAttribute("utensilio", repository.findById(id).get());
        return "utensilioEditar";
    }

    @PostMapping("/administrador/servicios/utensilio/{id}/edit/save")
    public String saveEditUtensilio(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/utensilio";
    }

    @GetMapping("/administrador/servicios/utensilio/{id}/delete")
    public String deleteUtensilio(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/utensilio";
    }
}
