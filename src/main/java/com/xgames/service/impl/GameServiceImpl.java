package com.xgames.service.impl;

import com.xgames.model.Game;
import com.xgames.model.Platform;
import com.xgames.model.filter.GameFilter;
import com.xgames.repository.GamesRepository;
import com.xgames.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class GameServiceImpl implements GameService {

    private static final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Autowired
    private GamesRepository gamesRepository;

    @Override
    public Page<Game> findAllPageable(Pageable pageable) {
        return gamesRepository.findAll(pageable);
    }

    public boolean gameAlreadyExists(Game game) {
        boolean editMode = game.getCode() != null;
        if (editMode) {
            return gamesRepository.existsOtherGameDiferentFromThisCode(game.getTitle(),
                    game.getPlatform(), game.getFormat(), game.getCode());
        } else {
            return gamesRepository.existsByTitleAndPlatformAndFormat(game.getTitle(),
                    game.getPlatform(), game.getFormat());
        }
    }

    public void saveGame(Game game) {
        logger.info("Saving game: {}", game);
        gamesRepository.save(game);
    }

    public Page<Game> filter(GameFilter gameFilter, Pageable pageable) {
        String gameTitle = gameFilter.getTitle() == null ? "%" : gameFilter.getTitle();
        Platform gamePlatform = gameFilter.getPlatform();
        BigDecimal min = gameFilter.getMinPrice();
        BigDecimal max = gameFilter.getMaxPrice();

        Page<Game> games;

        if (gamePlatform != null) {
            if (min != null && max != null) {
                logger.info("Searching by title '{}', platform '{}', minPrice '{}' and maxPrice '{}'", gameTitle,
                        gamePlatform, min, max);
                games = gamesRepository.findByTitleAndPlatformAndPrices(gameTitle, gamePlatform, min, max, pageable);
            } else {
                logger.info("Searching by title '{}' and platform '{}'", gameTitle, gamePlatform);
                games = gamesRepository.findDistinctByTitleContainingAndPlatform(gameTitle, gamePlatform, pageable);
            }
        } else if (min != null && max != null) {
            logger.info("Searching by title '{}', minPrice '{}' and maxPrice '{}'", gameTitle, min, max);
            games = gamesRepository.findByTitleAndPrices(gameTitle, min, max, pageable);
        } else {
            logger.info("Searching only by title '{}'", gameTitle);
            games = gamesRepository.findByTitleContaining(gameTitle, pageable);
        }

        logger.info("Number of records found: " + games.getTotalElements());
        return games;
    }
}
