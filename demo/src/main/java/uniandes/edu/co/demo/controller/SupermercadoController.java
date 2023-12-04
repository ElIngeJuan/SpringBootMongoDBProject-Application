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
public class SupermercadoController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/Supermercados")
    public String supermercado(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Supermercado"));
        return "Supermercado";
    }

    @GetMapping("/administrador/servicios/Supermercados/new")
    public String supermercadoNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "supermercadoNuevo";
    }

    @PostMapping("/administrador/servicios/Supermercados/new/save")
    public String supermercadoSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/Supermercados";
    } 

    @GetMapping("/administrador/servicios/Supermercados/{id}/edit")
    public String supermercadoEdit(Model model, @PathVariable String id) {
        model.addAttribute("servicio", repository.findById(id));
        return "supermercadoEditar";
    }
    
    @PostMapping("/administrador/servicios/Supermercados/{id}/edit/save")
    public String supermercadoEditSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/Supermercados";
    }
    
    @GetMapping("/administrador/servicios/Supermercados/{id}/delete")
    public String supermercadoDelete(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/Supermercados";
    }




    @GetMapping("/administrador/servicios/supermecado/productos/new")
    public String productosSupermercado(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Supermercado"));
        return "AgregarProductos";
    }

    @PostMapping("/administrador/servicios/supermecado/productos/new/save")
    public String productosSupermercadoSave(@RequestParam String id, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        if (servicio.getListaProductos() == null) {
            servicio.setListaProductos(new ArrayList<>());
        }
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/Supermercados";
    }

    @GetMapping("/administrador/servicios/supermecado/productos/{id}/{producto1}/edit")
    public String productosSupermercadoEdit(Model model, @PathVariable String id, @PathVariable String producto1) {
        model.addAttribute("servicios", repository.findByTipo("Supermercado"));
        return "AgregarProductosEditar";
    }

    @PostMapping("/administrador/servicios/supermecado/productos/{id}/{producto1}/edit/save")
    public String productosSupermercadoSaveEdit(@PathVariable String id, @PathVariable String producto1, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/Supermercados";

    }

    @GetMapping("/administrador/servicios/supermecado/productos/{id}/{producto1}/delete")
    public String productosSupermercadoDelete(@PathVariable String id, @PathVariable String producto1 ) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        repository.save(servicio);
        return "redirect:/administrador/servicios/Supermercados";
    }


}
