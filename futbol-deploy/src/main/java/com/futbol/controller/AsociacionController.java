package com.futbol.controller;

import com.futbol.model.Asociacion;
import com.futbol.repository.AsociacionRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/asociaciones")
public class AsociacionController {

    private final AsociacionRepository asociacionRepo;

    public AsociacionController(AsociacionRepository asociacionRepo) {
        this.asociacionRepo = asociacionRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        return "asociacion/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("asociacion", new Asociacion());
        model.addAttribute("modoEdicion", false);
        return "asociacion/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute Asociacion asociacion,
                          BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", false);
            return "asociacion/formulario";
        }
        asociacionRepo.save(asociacion);
        flash.addFlashAttribute("exito", "Asociación creada exitosamente");
        return "redirect:/asociaciones";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return asociacionRepo.findById(id).map(a -> {
            model.addAttribute("asociacion", a);
            model.addAttribute("modoEdicion", true);
            return "asociacion/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "Asociación no encontrada");
            return "redirect:/asociaciones";
        });
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Asociacion asociacion,
                             BindingResult result, Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "asociacion/formulario";
        }
        asociacion.setId(id);
        asociacionRepo.save(asociacion);
        flash.addFlashAttribute("exito", "Asociación actualizada");
        return "redirect:/asociaciones";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (asociacionRepo.existsById(id)) {
            asociacionRepo.deleteById(id);
            flash.addFlashAttribute("exito", "Asociación eliminada");
        } else {
            flash.addFlashAttribute("error", "Asociación no encontrada");
        }
        return "redirect:/asociaciones";
    }
}
