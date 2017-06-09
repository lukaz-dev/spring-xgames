package com.xgames.service;

import com.xgames.model.Game;
import com.xgames.model.filter.GameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GameService {

    Page<Game> findAllPageable(Pageable pageable);

    boolean gameAlreadyExists(Game game);

    void saveGame(Game game);

    List<Game> filter(GameFilter gameFilter);
}
