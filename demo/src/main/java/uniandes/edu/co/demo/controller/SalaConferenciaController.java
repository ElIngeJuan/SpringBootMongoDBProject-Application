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
public class SalaConferenciaController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/salasconferencia")
    public String salasconferencia(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Sala de conferencias"));
        return "salasconferencia";
    }

    @GetMapping("/administrador/servicios/salasconferencia/new")
    public String salasconferenciaNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "salasconferenciaNuevo";
    }

    @PostMapping("/administrador/servicios/salasconferencia/new/save")
    public String salasconferenciaSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/salasconferencia";
    }

    @GetMapping("/administrador/servicios/salasconferencia/{id}/edit")
    public String salasconferenciaEdit( Model model,@PathVariable String id) {
        model.addAttribute("servicio", repository.findById(id).get());
        return "salasconferenciaEditar";
    }
    
    @PostMapping("/administrador/servicios/salasconferencia/{id}/edit/save")
    public String salasconferenciaUpdate(Servicio servicio, @PathVariable String id) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/salasconferencia";
    }

    @GetMapping("/administrador/servicios/salasconferencia/{id}/delete")
    public String salasconferenciaDelete(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/salasconferencia";
    }

}
