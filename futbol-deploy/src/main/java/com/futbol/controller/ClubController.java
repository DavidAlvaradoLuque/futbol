package com.futbol.controller;

import com.futbol.model.Club;
import com.futbol.model.Competicion;
import com.futbol.repository.*;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/clubes")
public class ClubController {

    private final ClubRepository clubRepo;
    private final EntrenadorRepository entrenadorRepo;
    private final AsociacionRepository asociacionRepo;
    private final CompeticionRepository competicionRepo;

    public ClubController(ClubRepository clubRepo, EntrenadorRepository entrenadorRepo,
                          AsociacionRepository asociacionRepo, CompeticionRepository competicionRepo) {
        this.clubRepo = clubRepo;
        this.entrenadorRepo = entrenadorRepo;
        this.asociacionRepo = asociacionRepo;
        this.competicionRepo = competicionRepo;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clubes", clubRepo.findAll());
        return "club/lista";
    }

    @GetMapping("/{id}")
    public String ver(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return clubRepo.findById(id).map(club -> {
            model.addAttribute("club", club);
            return "club/detalle";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "Club no encontrado");
            return "redirect:/clubes";
        });
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("club", new Club());
        model.addAttribute("entrenadores", entrenadorRepo.findAll());
        model.addAttribute("asociaciones", asociacionRepo.findAll());
        model.addAttribute("competiciones", competicionRepo.findAll());
        model.addAttribute("modoEdicion", false);
        return "club/formulario";
    }

    @PostMapping("/nuevo")
    public String guardar(@Valid @ModelAttribute Club club,
                          BindingResult result,
                          @RequestParam(required = false) List<Long> competicionIds,
                          Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("entrenadores", entrenadorRepo.findAll());
            model.addAttribute("asociaciones", asociacionRepo.findAll());
            model.addAttribute("competiciones", competicionRepo.findAll());
            model.addAttribute("modoEdicion", false);
            return "club/formulario";
        }
        if (competicionIds != null) {
            List<Competicion> comps = competicionRepo.findAllById(competicionIds);
            club.setCompeticiones(comps);
        }
        clubRepo.save(club);
        flash.addFlashAttribute("exito", "Club creado exitosamente");
        return "redirect:/clubes";
    }

    @GetMapping("/{id}/editar")
    public String formularioEditar(@PathVariable Long id, Model model, RedirectAttributes flash) {
        return clubRepo.findById(id).map(club -> {
            model.addAttribute("club", club);
            model.addAttribute("entrenadores", entrenadorRepo.findAll());
            model.addAttribute("asociaciones", asociacionRepo.findAll());
            model.addAttribute("competiciones", competicionRepo.findAll());
            model.addAttribute("modoEdicion", true);
            return "club/formulario";
        }).orElseGet(() -> {
            flash.addFlashAttribute("error", "Club no encontrado");
            return "redirect:/clubes";
        });
    }

    @PostMapping("/{id}/editar")
    public String actualizar(@PathVariable Long id,
                             @Valid @ModelAttribute Club club,
                             BindingResult result,
                             @RequestParam(required = false) List<Long> competicionIds,
                             Model model, RedirectAttributes flash) {
        if (result.hasErrors()) {
            model.addAttribute("entrenadores", entrenadorRepo.findAll());
            model.addAttribute("asociaciones", asociacionRepo.findAll());
            model.addAttribute("competiciones", competicionRepo.findAll());
            model.addAttribute("modoEdicion", true);
            return "club/formulario";
        }
        club.setId(id);
        if (competicionIds != null) {
            club.setCompeticiones(competicionRepo.findAllById(competicionIds));
        }
        clubRepo.save(club);
        flash.addFlashAttribute("exito", "Club actualizado correctamente");
        return "redirect:/clubes/" + id;
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id, RedirectAttributes flash) {
        if (clubRepo.existsById(id)) {
            clubRepo.deleteById(id);
            flash.addFlashAttribute("exito", "Club eliminado");
        } else {
            flash.addFlashAttribute("error", "Club no encontrado");
        }
        return "redirect:/clubes";
    }
}
