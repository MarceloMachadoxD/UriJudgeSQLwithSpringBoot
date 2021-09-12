package com.devsuperior.uri2611.repositories;

import com.devsuperior.uri2611.DTO.MovieMinDTO;
import com.devsuperior.uri2611.entities.Movie;
import com.devsuperior.uri2611.projections.MovieMinProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(nativeQuery = true, value="SELECT movies.id, movies.name " +
        "FROM movies " +
        "INNER JOIN genres ON movies.id_genres = genres.id " +
        "WHERE INITCAP(genres.description) = INITCAP(:genreName)")
    List<MovieMinProjection> search1(String genreName);

    @Query("SELECT new com.devsuperior.uri2611.DTO.MovieMinDTO(obj.id, obj.name)   " +
        "FROM Movie obj " +
        "WHERE INITCAP(obj.genre.description) = INITCAP(:genreName)")
    List<MovieMinDTO> search2(String genreName);
}
