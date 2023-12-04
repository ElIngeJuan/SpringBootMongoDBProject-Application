package uniandes.edu.co.demo.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.demo.modelo.Habitacion;
import uniandes.edu.co.demo.repository.TipoHabitacionRepository;



@Controller
public class HabitacionController {
    @Autowired
    private TipoHabitacionRepository repository;

    
    @GetMapping("/administrador/habitaciones")
    public String TiposUsuario(Model model) {
        model.addAttribute("habitaciones", repository.findAll());
        return "habitacion";
    }

    @GetMapping("/administrador/habitaciones/new")
    public String habitacionForm(Model model) {
        model.addAttribute("Tipos", repository.findAll());
        model.addAttribute("habitaciones", new Habitacion());
        return "habitacionNuevo";
    }

    @PostMapping("/administrador/habitaciones/new/save")
    public String habitacionGuardar(
            @ModelAttribute Habitacion habitacion,
            @ModelAttribute("idh") Integer idBar,
            @ModelAttribute("id") Integer id
    ) {
        System.out.println(habitacion.getId());
        repository.aniadirHabitacionATipoHabitacion(idBar, id, habitacion.getValor_noche());
        return "redirect:/administrador/habitaciones";
    }


    @GetMapping("/administrador/habitaciones/{id}/{id2}/edit")
    public String TipoHabitacionEditar(@PathVariable Integer id, @PathVariable Integer id2, Model model) {
        Habitacion habitacion = repository.buscarTipoHabitacionPorIdYHabitacionId(id, id2);
        if (habitacion != null) {
            model.addAttribute("habitaciones", habitacion);
            model.addAttribute("Tipos", repository.findAll());
            return "habitacionEditar";
        } else {
            return "redirect:/administrador/habitaciones";
        }
    }

    @PostMapping("/administrador/habitaciones/{id}/{id2}/edit/save")
    public String TipoHabitacionActualizar(@PathVariable Integer id, @PathVariable Integer id2,@ModelAttribute Habitacion habitacion) {
        repository.editarValorNocheDeHabitacionEnTipoHabitacion(id, id2, habitacion.getValor_noche());
        return "redirect:/administrador/habitaciones";
    }

    @GetMapping("/administrador/habitaciones/{id}/{id2}/delete")
    public String habitacionesEliminar(@PathVariable Integer id, @PathVariable Integer id2) {
        repository.eliminarHabitacionDeTipoHabitacion(id,id2);
        return "redirect:/administrador/habitaciones";
    }
}


    
