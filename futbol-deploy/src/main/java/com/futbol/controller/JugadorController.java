package com.futbol.controller;

import com.futbol.model.Jugador;
import com.futbol.repository.ClubRepository;
import com.futbol.repository.JugadorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorRepository jugadorRepo;
    private final ClubRepository clubRepo;

    public JugadorController(JugadorRepository jugadorRepo, ClubRepository clubRepo) {
        this.jugadorRepo = jugadorRepo;
        this.clubRepo = clubRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("jugadores", jugadorRepo.findAll());
        return "jugador/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("jugador", new Jugador());
        model.addAttribute("clubes", clubRepo.findAll());
        model.addAttribute("modoEdicion", false);
        return "jugador/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute Jugador jugador,
                          BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("clubes", clubRepo.findAll());
            model.addAttribute("modoEdicion", false);
            return "jugador/formulario";
        }
        jugadorRepo.save(jugador);
        flash.addFlashAttribute("exito", "Jugador registrado exitosamente");
        return "redirect:/jugadores";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return jugadorRepo.findById(id).map(j -> {
            model.addAttribute("jugador", j);
            model.addAttribute("clubes", clubRepo.findAll());
            model.addAttribute("modoEdicion", true);
            return "jugador/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "Jugador no encontrado");
            return "redirect:/jugadores";
        });
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Jugador jugador,
                             BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("clubes", clubRepo.findAll());
            model.addAttribute("modoEdicion", true);
            return "jugador/formulario";
        }
        jugador.setId(id);
        jugadorRepo.save(jugador);
        flash.addFlashAttribute("exito", "Jugador actualizado correctamente");
        return "redirect:/jugadores";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (jugadorRepo.existsById(id)) {
            jugadorRepo.deleteById(id);
            flash.addFlashAttribute("exito", "Jugador eliminado");
        } else {
            flash.addFlashAttribute("error", "Jugador no encontrado");
        }
        return "redirect:/jugadores";
    }
}
