package com.xgames.service;

import com.xgames.model.Game;
import com.xgames.model.Platform;
import com.xgames.model.filter.GameFilter;
import com.xgames.repository.GamesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaveGameService {

    private static final Logger logger = LoggerFactory.getLogger(SaveGameService.class);

    @Autowired
    private GamesRepository gamesRepository;

    public boolean gameAlreadyExists(Game game) {
        boolean editMode = game.getCode() != null;

        if (editMode) {
            return gamesRepository.existsOtherGameDiferentFromThisCode(game.getTitle(), game.getPlatform(), game.getFormat(), game.getCode());
        } else {
            return gamesRepository.existsByTitleAndPlatformAndFormat(game.getTitle(), game.getPlatform(), game.getFormat());
        }
    }

    public void saveGame(Game game) {
        logger.info("Saving game: {}", game);
        gamesRepository.save(game);
    }

    public List<Game> filter(GameFilter gameFilter) {
        Sort sort = new Sort(Sort.Direction.ASC, "title", "platform", "price");
        String gameTitle = gameFilter.getTitle() == null ? "%" : gameFilter.getTitle();
        Platform gamePlatform = gameFilter.getPlatform();
        BigDecimal min = gameFilter.getMinPrice();
        BigDecimal max = gameFilter.getMaxPrice();

        List<Game> games;

        if (gamePlatform != null) {
            if (min != null && max != null) {
                logger.info("Searching by title '{}', platform '{}', minPrice '{}' and maxPrice '{}'", gameTitle,
                        gamePlatform, min, max);
                games = gamesRepository.findByTitleAndPlatformAndPrices(gameTitle, gamePlatform, min, max, sort);
            } else {
                logger.info("Searching by title '{}' and platform '{}'", gameTitle, gamePlatform);
                games = gamesRepository.findDistinctByTitleContainingAndPlatform(gameTitle, gamePlatform, sort);
            }
        } else if (min != null && max != null) {
            logger.info("Searching by title '{}', minPrice '{}' and maxPrice '{}'", gameTitle, min, max);
            games = gamesRepository.findByTitleAndPrices(gameTitle, min, max, sort);
        } else {
            logger.info("Searching only by title '{}'", gameTitle);
            games = gamesRepository.findByTitleContaining(gameTitle, sort);
        }

        logger.info("Number of records found: " + games.size());
        return games;
    }
}
