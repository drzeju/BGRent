package com.example.BGRent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GamesRepository extends JpaRepository<GameEntity, Long>
{


    List<GameEntity> findAll();
    Optional<GameEntity> findById(Long gameId);

    // See also:
    //
    //     1. @Query annotation
    //     2. Specifications / JpaSpecificationExecutor
}