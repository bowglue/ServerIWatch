package com.example.webapp.serverapp.repositories;

import com.example.webapp.serverapp.models.Trailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrailerRepository extends JpaRepository<Trailer, Long> {

    @Query(value = "SELECT * from trailer  where movie_id = ?1 and segment_id = ?2",  nativeQuery = true)
    Optional<Trailer> getSegment(Long movieId, Long segmentId);
}
