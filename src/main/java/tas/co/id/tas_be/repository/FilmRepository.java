package tas.co.id.tas_be.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tas.co.id.tas_be.model.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    Boolean existsByNama(String nama);
}
