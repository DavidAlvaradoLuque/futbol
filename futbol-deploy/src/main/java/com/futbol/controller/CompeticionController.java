package com.futbol.controller;

import com.futbol.model.Competicion;
import com.futbol.repository.CompeticionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/competiciones")
public class CompeticionController {

    private final CompeticionRepository competicionRepo;

    public CompeticionController(CompeticionRepository competicionRepo) {
        this.competicionRepo = competicionRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("competiciones", competicionRepo.findAll());
        return "competicion/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("competicion", new Competicion());
        model.addAttribute("modoEdicion", false);
        return "competicion/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute Competicion competicion,
                          BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "competicion/formulario";
        }
        competicionRepo.save(competicion);
        flash.addFlashAttribute("exito", "Competición creada exitosamente");
        return "redirect:/competiciones";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return competicionRepo.findById(id).map(c -> {
            model.addAttribute("competicion", c);
            model.addAttribute("modoEdicion", true);
            return "competicion/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "Competición no encontrada");
            return "redirect:/competiciones";
        });
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Competicion competicion,
                             BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "competicion/formulario";
        }
        competicion.setId(id);
        competicionRepo.save(competicion);
        flash.addFlashAttribute("exito", "Competición actualizada");
        return "redirect:/competiciones";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (competicionRepo.existsById(id)) {
            competicionRepo.deleteById(id);
            flash.addFlashAttribute("exito", "Competición eliminada");
        } else {
            flash.addFlashAttribute("error", "Competición no encontrada");
        }
        return "redirect:/competiciones";
    }
}
