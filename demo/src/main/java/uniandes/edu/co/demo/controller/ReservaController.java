package uniandes.edu.co.demo.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import uniandes.edu.co.demo.modelo.Reserva;
import uniandes.edu.co.demo.modelo.UsuarioPresencia;
import uniandes.edu.co.demo.repository.ReservaRepository;
import uniandes.edu.co.demo.repository.UsuarioPresenciaRepository;

@Controller
public class ReservaController {

    @Autowired
    private UsuarioPresenciaRepository usuariorepository;

    @Autowired
    private ReservaRepository repository;
    
    @GetMapping("/cliente/reserva")
    public String reserva(Model model) {
        model.addAttribute("Reservas", repository.findAll());
        return "reserva";
    }
    
    @GetMapping("/cliente/reserva/new")
    public String reservaNew(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("usuario_presencia", new UsuarioPresencia());
        return "reservaNuevo";
    }

    @PostMapping("/cliente/reserva/new/save")
    public String reservaSave(UsuarioPresencia usuario,Reserva reserva, Model model) {
        Collection<Reserva> reservas = repository.buscarId();
        if (reservas.isEmpty()) {
            reserva.setId(1);
            usuario.setDocumento(reserva.getUsuario_presencia());
            usuario.setReserva(1);
            repository.save(reserva);
            usuariorepository.save(usuario);
        }
        else{
            Integer id = repository.buscarId().iterator().next().getId() + 1;
            reserva.setId(id);
            usuario.setDocumento(reserva.getUsuario_presencia());
            usuario.setReserva(id);
            repository.save(reserva);
            usuariorepository.save(usuario);

        }
        return "redirect:/cliente/reserva";
    }

    @GetMapping("/cliente/reserva/{id}/edit")
    public String reservaEdit(Model model,@PathVariable String id) {
        model.addAttribute("reserva", repository.findById(Integer.parseInt(id)));
        return "reservaEditar";
    }

    @PostMapping("/cliente/reserva/{id}/edit/save")
    public String reservaEditSave(Reserva reserva,@PathVariable Model model) {
        repository.save(reserva);
        return "redirect:/cliente/reserva";
    }

    @GetMapping("/cliente/reserva/{id}/delete")
    public String reservaDelete( @PathVariable String id) {
        repository.deleteById(Integer.parseInt(id));
        return "redirect:/cliente/reserva";
    }

}
