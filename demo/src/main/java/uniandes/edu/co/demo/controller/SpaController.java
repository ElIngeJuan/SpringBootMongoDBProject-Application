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
public class SpaController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/spa")
    public String spa(Model model) {
        model.addAttribute("servicios", repository.findByTipo("SPA"));
        return "spa";
    }

    @GetMapping("/administrador/servicios/spa/new")
    public String newSpa(Model model) {
        model.addAttribute("spa", new Servicio());
        return "SpaNuevo";
    }

    @PostMapping("/administrador/servicios/spa/new/save")
    public String saveSpa(Servicio servicio) {
        servicio.setTipo("SPA");
        repository.save(servicio);
        return "redirect:/administrador/servicios/spa";
    }

    @GetMapping("/administrador/servicios/spa/{id}/edit")
    public String editSpa(Model model, @PathVariable String id) {
        model.addAttribute("spa", repository.findById(id).get());
        return "spaEditar";
    }

    @PostMapping("/administrador/servicios/spa/{id}/edit/save")
    public String saveEditSpa(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/spa";
    }

    @GetMapping("/administrador/servicios/spa/{id}/delete")
    public String deleteSpa(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/spa";
    }
}
