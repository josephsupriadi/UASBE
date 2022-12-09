package tas.co.id.tas_be.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tas.co.id.tas_be.model.Film;
import tas.co.id.tas_be.service.FilmService;

@RestController
@RequestMapping("/film")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

   
    @GetMapping
    public List<Film> getAll() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities()                );
        return filmService.getAll();
    }

    @GetMapping("/{id}")
    public Film getById(@PathVariable Long id) {
        return filmService.getById(id);
    }

  
    @PostMapping
    public Film create(@RequestBody Film film) {
        return filmService.create(film);
    }

  
    @PutMapping("/{id}")
    public Film update(@PathVariable Long id, @RequestBody Film film) {
        return filmService.update(id, film);
    }

    @DeleteMapping("/{id}")
    public Film delete(@PathVariable Long id) {
        return filmService.delete(id);
    }
}
