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
public class InternetController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/internet")
    public String internet(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Internet"));
        return "internet";
    }

    @GetMapping("/administrador/servicios/internet/new")
    public String internetNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "internetNuevo";
    }

    @PostMapping("/administrador/servicios/internet/new/save")
    public String internetSave(Servicio servicio) {
        servicio.setTipo("Internet");
        repository.save(servicio);
        return "redirect:/administrador/servicios/internet";
    }

    @GetMapping("/administrador/servicios/internet/{id}/edit")
    public String internetEdit(Model model, @PathVariable String id) {
        model.addAttribute("servicio", repository.findById(id).get());
        return "internetEditar";
    }

    @PostMapping("/administrador/servicios/internet/{id}/edit/save")
    public String internetSaveEdit(Servicio servicio, @PathVariable String id) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/internet";
    }

    @GetMapping("/administrador/servicios/internet/{id}/delete")
    public String internetDelete(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/internet";
    }
}
