package uniandes.edu.co.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import uniandes.edu.co.demo.modelo.Reserva;
import uniandes.edu.co.demo.repository.ReservaRepository;


@Controller
public class ConsumoController {
    
    @Autowired
    private ReservaRepository repository;


    @GetMapping("/empleado/consumoarea")
    public String consumoarea(Model model) {
        return "consumoAreasForm";
    }

    @PostMapping("/empleado/consumoarea")
    public String areasresultado(Model model, @RequestParam Integer id){
        model.addAttribute("documento",id.toString());
        model.addAttribute("usuarioinfo",repository.buscarDocumentos(id));
        return "consumoAreas";
    }

    @GetMapping("/empleado/consumoarea/{id}/new")
    public String habitacionForm(Model model, @PathVariable Integer id) {
        model.addAttribute("usuarioinfo",repository.buscarDocumentos(id));
        return "consumoAreasNuevo";
    }

    @PostMapping("/empleado/consumoarea/{id}/new/save")
    public String habitacionSave(@ModelAttribute Reserva reserva, Model model, @RequestParam String id, @RequestParam String servicio, @RequestParam String fecha, @RequestParam Integer valor) {
        repository.aniadirHabitacionATipoHabitacion( Integer.parseInt(id),servicio, fecha, valor);
        return "redirect:/empleado/consumoarea";
    }

    @GetMapping("/empleado/consumoarea/{id}/{servicio}/{fecha}/{valor}/edit")
    public String habitacionEdit(Model model, @PathVariable String id, @PathVariable String servicio, @PathVariable String fecha, @PathVariable Integer valor) {
        model.addAttribute("reserva",repository.buscarTipoHabitacionPorIdYHabitacionId(Integer.parseInt(id),servicio, fecha, valor));
        return "consumoAreasEditar";
    }

    @PostMapping("/empleado/consumoarea/{id}/{servicio1}/{fecha1}/{valor1}/edit/save")
    public String habitacionUpdate( Model model, @PathVariable String id, @PathVariable String servicio1, @PathVariable String fecha1, @PathVariable Integer valor1,@RequestParam String servicio, @RequestParam String fecha, @RequestParam Integer valor) {
        repository.editarValorNocheDeHabitacionEnTipoHabitacion(Integer.parseInt(id),servicio1, fecha1, valor1, servicio, fecha, valor);                     
        return "redirect:/empleado/consumoarea";
    }

    @GetMapping("/empleado/consumoarea/{id}/{servicio1}/{fecha1}/{valor1}/delete")
    public String habitacionDelete(Model model, @PathVariable String id, @PathVariable String servicio1, @PathVariable String fecha1, @PathVariable Integer valor1) {
        repository.eliminarHabitacionDeTipoHabitacion(Integer.parseInt(id),servicio1, fecha1, valor1);
        return "redirect:/empleado/consumoarea";
    }
}
