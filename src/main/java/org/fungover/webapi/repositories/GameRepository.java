package org.fungover.webapi.repositories;

import org.fungover.webapi.entities.Game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

public List<Game> findAllByPlayerOneOrPlayerTwo(String playerOne,String playerTwo);


}
