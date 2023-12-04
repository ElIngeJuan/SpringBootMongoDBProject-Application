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
public class TiendaController {
    @Autowired
    private ServicioRepository repository;

    @GetMapping("/administrador/servicios/tiendas")
    public String tienda(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Tienda"));
        return "Tienda";
    }

    @GetMapping("/administrador/servicios/tiendas/new")
    public String tiendaNew(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "TiendaNuevo";
    }

    @PostMapping("/administrador/servicios/tiendas/new/save")
    public String tiendaSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/tiendas";
    }

    @GetMapping("/administrador/servicios/tiendas/{id}/edit")
    public String tiendaEdit(Model model, @PathVariable String id) {
        model.addAttribute("servicio", repository.findById(id).get());
        return "TiendaEditar";
    }

    @PostMapping("/administrador/servicios/tiendas/{id}/edit/save")
    public String tiendaEditSave(Servicio servicio) {
        repository.save(servicio);
        return "redirect:/administrador/servicios/tiendas";
    }

    @GetMapping("/administrador/servicios/tiendas/{id}/delete")
    public String tiendaDelete(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/servicios/tiendas";
    }


    @GetMapping("/administrador/servicios/tiendas/productos/new")
    public String productosTienda(Model model) {
        model.addAttribute("servicios", repository.findByTipo("Tienda"));
        return "AgregarProductos";
    }

    @PostMapping("/administrador/servicios/tiendas/productos/new/save")
    public String productosTiendaSave(@RequestParam String id, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        if (servicio.getListaProductos() == null) {
            servicio.setListaProductos(new ArrayList<>());
        }
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/tiendas";
    }

    @GetMapping("/administrador/servicios/tiendas/productos/{id}/{producto1}/edit")
    public String productosTiendaEdit(Model model, @PathVariable String id, @PathVariable String producto1) {
        model.addAttribute("servicios", repository.findByTipo("Tienda"));
        return "AgregarProductosEditar";
    }

    @PostMapping("/administrador/servicios/tiendas/productos/{id}/{producto1}/edit/save")
    public String productosTiendaSaveEdit(@PathVariable String id, @PathVariable String producto1, @RequestParam String producto) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        servicio.getListaProductos().add(producto);
        repository.save(servicio);
        return "redirect:/administrador/servicios/tiendas";

    }

    @GetMapping("/administrador/servicios/tiendas/productos/{id}/{producto1}/delete")
    public String productosTiendaDelete(@PathVariable String id, @PathVariable String producto1 ) {
        Servicio servicio = repository.findById(id).get();
        servicio.getListaProductos().remove(producto1);
        repository.save(servicio);
        return "redirect:/administrador/servicios/tiendas";
    }


    




}
