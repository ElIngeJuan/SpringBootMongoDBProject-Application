package uniandes.edu.co.demo.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import uniandes.edu.co.demo.modelo.Servicio;
import uniandes.edu.co.demo.repository.ServicioRepository;

@Controller
public class RestauranteController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/restaurantes")
    public String restaurante(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Restaurante"));
        return "restaurante";
    }

    @GetMapping("/administrador/servicios/restaurantes/new")
    public String restauranteNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "restauranteNuevo";
    }

    @PostMapping("/administrador/servicios/restaurantes/new/save")
    public String restauranteSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/restaurantes";
    }

    @GetMapping("/administrador/servicios/restaurantes/{id}/edit")
    public String restauranteEdit(Model model, @PathVariable String id) {
        model.addAttribute("servicio", repository.findById(id).get());
        return "restauranteEditar";
    }

    @PostMapping("/administrador/servicios/restaurantes/{id}/edit/save")
    public String restauranteSaveEdit(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/restaurantes";
    }

    @GetMapping("/administrador/servicios/restaurantes/{id}/delete")
    public String restauranteDelete(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/restaurantes";
    }

    @GetMapping("/administrador/servicios/productosrest/new")
    public String productos(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Restaurante"));
        return "AgregarProductos";
    }

    @PostMapping("/administrador/servicios/productosrest/new/save")
    public String productosSave(@RequestParam String id, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        if (servicio.getListaProductos() == null) {
            servicio.setListaProductos(new ArrayList<>());
        }
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/restaurantes";
    }

    @GetMapping("/administrador/servicios/productosrest/{id}/{producto1}/edit")
    public String productosEdit(Model model, @PathVariable String id, @PathVariable String producto1) {
        model.addAttribute("servicios", repository.findByTipo("Restaurante"));
        return "AgregarProductosEditar";
    }

    @PostMapping("/administrador/servicios/productosrest/{id}/{producto1}/edit/save")
    public String productosSaveEdit(@PathVariable String id, @PathVariable String producto1, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/restaurantes";
    }

    @GetMapping("/administrador/servicios/productosrest/{id}/{producto1}/delete")
    public String productosDelete(@PathVariable String id, @PathVariable String producto1 ) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        repository.save(servicio);
        return "redirect:/administrador/servicios/restaurantes";
    }


    


    
}
