package uniandes.edu.co.demo.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.TipoHabitacion;
import uniandes.edu.co.demo.repository.TipoHabitacionRepository;


@Controller
public class TipoHabitacionController {
    @Autowired
    private TipoHabitacionRepository repository;

    @GetMapping("/administrador/tipohabitacion")
    public String TiposUsuario(Model model) {
        model.addAttribute("TipoHabitacion", repository.findAll());
        return "tiposHabitaciones";
    }

    @GetMapping("/administrador/tipohabitacion/new")
    public String TipoHabitacionForm(Model model) {
        model.addAttribute("TipoHabitacion", new TipoHabitacion());
        return "tiposHabitacionesNuevo";
    }

    @PostMapping("/administrador/tipohabitacion/new/save")
    public String TipoHabitacionGuardar(@ModelAttribute TipoHabitacion tipoHabitacion) {
        Collection  <TipoHabitacion> tipoHabitaciones = repository.buscarId();
        if (tipoHabitaciones.isEmpty()) {
            tipoHabitacion.setId(1);
        }
        else{
            Integer id = repository.buscarId().iterator().next().getId() + 1;
            tipoHabitacion.setId(id);
        }
        repository.save(tipoHabitacion);
        return "redirect:/administrador/tipohabitacion";
    }


    @GetMapping("/administrador/tipohabitacion/{id}/edit")
    public String TipoHabitacionEditar(@PathVariable Integer id, Model model) {
        Optional<TipoHabitacion> tipoHabitacionOptional = repository.findById(id);
        if (tipoHabitacionOptional.isPresent()) {
            model.addAttribute("TipoHabitacion", tipoHabitacionOptional.get());
            return "tiposHabitacionesEditar";
        } else {
            return "redirect:/administrador/tipohabitacion";
        }
    }

    @PostMapping("/administrador/tipohabitacion/{id}/edit/save")
    public String TipoHabitacionActualizar(@ModelAttribute TipoHabitacion tipoHabitacion) {
        repository.save(tipoHabitacion);
        return "redirect:/administrador/tipohabitacion";
    }

    @GetMapping("/administrador/tipohabitacion/{id}/delete")
    public String TipoHabitacionEliminar(@PathVariable Integer id) {
        Optional<TipoHabitacion> tipoHabitacionOptional = repository.findById(id);
        if (tipoHabitacionOptional.isPresent()) {
            repository.deleteById(id);
            return "redirect:/administrador/tipohabitacion";
        } else {
            return "redirect:/administrador/tipohabitacion";
        }
    }

}
