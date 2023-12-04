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
public class BarController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/bares")
    public String bar(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Bar"));
        return "bares";
    }

    @GetMapping("/administrador/servicios/bares/new")
    public String barNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "baresNuevo";
    }

    @PostMapping("/administrador/servicios/bares/new/save")
    public String barSave(Servicio servicio) {
        servicio.setTipo("Bar");
        repository.save(servicio);
        return "redirect:/administrador/servicios/bares";
    }

    

    @GetMapping("/administrador/servicios/bares/{id}/edit")
    public String barEdit(Model model, @PathVariable String id) {
        model.addAttribute("servicio", repository.findById(id).get());
        return "baresEditar";
    }

    @PostMapping("/administrador/servicios/bares/{id}/edit/save")
    public String barSaveEdit(Servicio servicio, String id) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/bares";
    }

    @GetMapping("/administrador/servicios/bares/{id}/delete")
    public String barDelete(String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/bares";
    }
    

    @GetMapping("/administrador/servicios/productos/new")
    public String productos(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Bar"));
        return "AgregarProductos";
    }

    @PostMapping("/administrador/servicios/productos/new/save")
    public String productosSave(@RequestParam String id, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        if (servicio.getListaProductos() == null) {
            servicio.setListaProductos(new ArrayList<>());
        }
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/bares";
    }

    @GetMapping("/administrador/servicios/productos/{id}/{producto1}/edit")
    public String productosEdit(Model model, @PathVariable String id, @PathVariable String producto1) {
        model.addAttribute("servicios", repository.findByTipo("Bar"));
        return "AgregarProductosEditar";
    }

    @PostMapping("/administrador/servicios/productos/{id}/{producto1}/edit/save")
    public String productosSaveEdit(@PathVariable String id, @PathVariable String producto1, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/bares";
    }

    @GetMapping("/administrador/servicios/productos/{id}/{producto1}/delete")
    public String productosDelete(@PathVariable String id, @PathVariable String producto1 ) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        repository.save(servicio);
        return "redirect:/administrador/servicios/bares";
    }

    
}
