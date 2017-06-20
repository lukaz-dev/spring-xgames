package com.xgames.service;

import com.xgames.model.Game;
import com.xgames.model.filter.GameFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GameService {

    Page<Game> findAllPageable(Pageable pageable);

    boolean gameAlreadyExists(Game game);

    void saveGame(Game game);

    Page<Game> filter(GameFilter gameFilter, Pageable pageable);
}
