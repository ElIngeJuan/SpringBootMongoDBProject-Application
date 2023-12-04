package uniandes.edu.co.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.demo.modelo.UsuarioPresencia;
import uniandes.edu.co.demo.repository.UsuarioPresenciaRepository;

@Controller
public class UsuarioPresenciaController {
    @Autowired
    private UsuarioPresenciaRepository repository;

    @GetMapping("/recepcionista/usuarioPresencia")
    public String TiposUsuario(Model model) {
        model.addAttribute("usuarioPresencia", repository.findAll());
        return "usuarioPresencia";
    }

    @GetMapping("/recepcionista/usuarioPresencia/new")
    public String UsuarioNuevo(Model model) {
        model.addAttribute("usuarioPresencia", new UsuarioPresencia());
        return "usuarioPresenciaNuevo";
    }

    @PostMapping("/recepcionista/usuarioPresencia/new/save")
    public String UsuarioGuardar(UsuarioPresencia usuario) {
        repository.save(usuario);
        return "redirect:/recepcionista/usuarioPresencia";
    }

    @GetMapping("/recepcionista/usuarioPresencia/{id}/edit")
    public String UsuarioEditar(@PathVariable String id, Model model) {
        Optional<UsuarioPresencia> UsuarioOptional = repository.findById(id);
        if (UsuarioOptional.isPresent()) {
            model.addAttribute("usuarioPresencia", UsuarioOptional.get());
            return "usuarioPresenciaEditar";
        } else {
            return "redirect:/recepcionista/usuarioPresencia";
        }
    }

    @PostMapping("/recepcionista/usuarioPresencia/{id}/edit/save")
    public String TipoHabitacionActualizar(@ModelAttribute UsuarioPresencia usuario, @PathVariable String id) {
        repository.save(usuario);
        return "redirect:/recepcionista/usuarioPresencia";
    }

    @GetMapping("/administrador/usuarioPresencia/{id}/delete")
    public String UsuarioBorrar(@PathVariable String id) {
        repository.deleteById(id);
        return "redirect:/administrador/usuarioPresencia";
    }


}
