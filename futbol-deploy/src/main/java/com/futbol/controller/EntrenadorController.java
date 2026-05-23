package com.futbol.controller;

import com.futbol.model.Entrenador;
import com.futbol.repository.EntrenadorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/entrenadores")
public class EntrenadorController {

    private final EntrenadorRepository entrenadorRepo;

    public EntrenadorController(EntrenadorRepository entrenadorRepo) {
        this.entrenadorRepo = entrenadorRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        return "entrenador/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("entrenador", new Entrenador());
        model.addAttribute("modoEdicion", false);
        return "entrenador/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute Entrenador entrenador,
                          BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "entrenador/formulario";
        }
        entrenadorRepo.save(entrenador);
        flash.addFlashAttribute("exito", "Entrenador registrado exitosamente");
        return "redirect:/entrenadores";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return entrenadorRepo.findById(id).map(e -> {
            model.addAttribute("entrenador", e);
            model.addAttribute("modoEdicion", true);
            return "entrenador/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "Entrenador no encontrado");
            return "redirect:/entrenadores";
        });
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Entrenador entrenador,
                             BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "entrenador/formulario";
        }
        entrenador.setId(id);
        entrenadorRepo.save(entrenador);
        flash.addFlashAttribute("exito", "Entrenador actualizado");
        return "redirect:/entrenadores";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (entrenadorRepo.existsById(id)) {
            entrenadorRepo.deleteById(id);
            flash.addFlashAttribute("exito", "Entrenador eliminado");
        } else {
            flash.addFlashAttribute("error", "Entrenador no encontrado");
        }
        return "redirect:/entrenadores";
    }
}
