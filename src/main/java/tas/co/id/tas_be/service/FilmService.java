package tas.co.id.tas_be.service;

import java.util.List;
import lombok.AllArgsConstructor;
import tas.co.id.tas_be.model.Film;
import tas.co.id.tas_be.repository.FilmRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class FilmService {

    private FilmRepository filmRepository;

    public List<Film> getAll() {
        return filmRepository.findAll();
    }

    public Film getById(Long id) {
        return filmRepository
                .findById(id)
                .orElseThrow(()
                        -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found")
                );
    }

    public Film create(Film film) {
        if (film.getId() != null) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "film already exists"
            );
        }
        if (filmRepository.existsByNama(film.getNama())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "film name already exists");
        }
        return filmRepository.save(film);
    }

    public Film update(Long id, Film film) {
        getById(id);
        film.setId(id);
        return filmRepository.save(film);
    }

    public Film delete(Long id) {
        Film film = getById(id);
        filmRepository.delete(film);
        return film;
    }
}
