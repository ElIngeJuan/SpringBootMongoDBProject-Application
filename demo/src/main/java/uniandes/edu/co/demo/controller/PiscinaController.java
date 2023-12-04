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
public class PiscinaController {

    @Autowired
    private ServicioRepository repository;
    
    @GetMapping("/administrador/servicios/piscina")
    public String piscina(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Piscina"));
        return "piscina";
    }

    @GetMapping("/administrador/servicios/piscina/new")
    public String newPiscina(Model model) {
        model.addAttribute("piscina", new Servicio());
        return "piscinaNuevo";
    }
    
    @PostMapping("/administrador/servicios/piscina/new/save")
    public String savePiscina(Servicio servicio) {
        servicio.setTipo("Piscina");
        repository.save(servicio);
        return "redirect:/administrador/servicios/piscina";
    }

    @GetMapping("/administrador/servicios/piscina/{id}/edit")
    public String editPiscina(Model model, @PathVariable String id) {
        model.addAttribute("piscina", repository.findById(id).get());
        return "piscinaEditar";
    }

    @PostMapping("/administrador/servicios/piscina/{id}/edit/save")
    public String saveEditPiscina(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/piscina";
    }

    @GetMapping("/administrador/servicios/piscina/{id}/delete")
    public String deletePiscina(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/piscina";
    }
}
