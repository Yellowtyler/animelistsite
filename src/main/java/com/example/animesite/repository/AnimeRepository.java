package com.example.animesite.repository;

import com.example.animesite.entity.Anime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnimeRepository extends CrudRepository<Anime, Long> {
}
