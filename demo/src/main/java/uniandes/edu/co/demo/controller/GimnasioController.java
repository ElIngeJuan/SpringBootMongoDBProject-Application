package uniandes.edu.co.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.repository.ServicioRepository;

@Controller
public class GimnasioController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/Gyms")
    public String Gym(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Gimnasio"));
        return "Gym";
    }

    @GetMapping("/administrador/servicios/Gyms/new")
    public String newGym(Model model) {
        model.addAttribute("Gyms", new Servicio());
        return "GymNuevo";
    }

    @PostMapping("/administrador/servicios/Gyms/new/save")
    public String saveGym(Servicio servicio) {
        servicio.setTipo("Gimnasio");
        repository.save(servicio);
        return "redirect:/administrador/servicios/Gyms";
    }


    @GetMapping("/administrador/servicios/Gyms/{id}/edit")
    public String editGym(Model model, @PathVariable String id) {
        model.addAttribute("Gyms", repository.findById(id).get());
        return "GymEditar";
    }

    @PostMapping("/administrador/servicios/Gyms/{id}/edit/save")
    public String saveEditGym(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/Gym";
    }

    @GetMapping("/administrador/servicios/Gyms/{id}/delete")
    public String deleteGym(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/Gym";
    }

    @GetMapping("/administrador/servicios/MaquinaGyms/Maquinas")
    public String MaquinaGym(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Gimnasio"));
        return "MaquinaGymNuevo";
    }

    @PostMapping("/administrador/servicios/MaquinaGyms/Maquinas/save")
    public String saveMaquinaGym(Model model, @RequestParam String id, @RequestParam String maquina) {
        Servicio servicio = repository.findById(id).orElse(new Servicio());
        if (servicio.getMaquinas() == null) {
            servicio.setMaquinas(new ArrayList<>());
        } 
        servicio.getMaquinas().add(maquina);
        repository.save(servicio); 
        return "redirect:/administrador/servicios/Gyms";
    }

    @GetMapping("/administrador/servicios/MaquinaGyms/Maquinas/{id}/{maquina}/edit")
    public String editMaquinaGym(Model model, @PathVariable String id, @PathVariable String maquina) {
        Servicio servicio = repository.findById(id).orElse(new Servicio());
        model.addAttribute("servicio", servicio);
        model.addAttribute("maquina", maquina);
        return "MaquinaGymEditar";
    }

    @PostMapping("/administrador/servicios/MaquinaGyms/Maquinas/{id}/{maquina}/edit/save")
    public String saveEditMaquinaGym(Model model, @PathVariable String id, @PathVariable String maquina, @RequestParam String maquina1) {
        Servicio servicio = repository.findById(id).orElse(new Servicio());
        servicio.getMaquinas().remove(maquina);
        servicio.getMaquinas().add(maquina1);
        repository.save(servicio);
        return "redirect:/administrador/servicios/Gyms";
    }


    @GetMapping("/administrador/servicios/MaquinaGyms/Maquinas/{id}/{maquina}/delete")
    public String deleteMaquinaGym(Model model, @PathVariable String id, @PathVariable String maquina) {
        Servicio servicio = repository.findById(id).orElse(new Servicio());
        servicio.getMaquinas().remove(maquina);
        repository.save(servicio);
        return "redirect:/administrador/servicios/Gyms";
    }
}
