package com.xgames.service;

import com.xgames.model.Game;
import com.xgames.model.filter.GameFilter;

import java.util.List;

public interface GameService {

    boolean gameAlreadyExists(Game game);

    void saveGame(Game game);

    List<Game> filter(GameFilter gameFilter);
}
