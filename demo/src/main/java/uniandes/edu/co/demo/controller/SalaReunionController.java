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
public class SalaReunionController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/salasreunion")
    public String salasreunion(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Sala de reuniones"));
        return "salasreunion";
    }

    @GetMapping("/administrador/servicios/salasreunion/new")
    public String salasreunionNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "salasreunionNuevo";
    }

    @PostMapping("/administrador/servicios/salasreunion/new/save")
    public String salasreunionSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/salasreunion";
    }

    @GetMapping("/administrador/servicios/salasreunion/{id}/edit")
    public String salasreunionEdit( Model model, @PathVariable("id") String id) {
        model.addAttribute("servicio", repository.findById(id).get());
        return "salasreunionEditar";
    }

    @PostMapping("/administrador/servicios/salasreunion/{id}/edit/save")
    public String salasreunionUpdate(Servicio servicio, @PathVariable("id") String id) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/salasreunion";
    }

    @GetMapping("/administrador/servicios/salasreunion/{id}/delete")
    public String salasreunionDelete(@PathVariable("id") String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/salasreunion";
    }
    

}
