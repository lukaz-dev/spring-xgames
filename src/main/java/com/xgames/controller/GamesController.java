package com.xgames.controller;

import com.xgames.model.Category;
import com.xgames.model.Format;
import com.xgames.model.Game;
import com.xgames.model.Platform;
import com.xgames.model.filter.GameFilter;
import com.xgames.repository.GamesRepository;
import com.xgames.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/games")
public class GamesController {

    private static final Logger logger = LoggerFactory.getLogger(GamesController.class);

    @Autowired
    private GamesRepository gamesRepository;

    @Autowired
    private GameService gameService;

    @GetMapping("/new")
    public ModelAndView newGame(Game game) {
        ModelAndView mv = new ModelAndView("games/new-game");
        mv.addObject(game);
        mv.addObject("categories", Category.values());
        mv.addObject("platforms", Platform.values());
        mv.addObject("formats", Format.values());
        mv.addObject("newActive", true);
        return mv;
    }

    @PostMapping("/new")
    @CacheEvict(value = "games", allEntries = true)
    public ModelAndView saveGame(@Valid Game game, BindingResult result, RedirectAttributes attributes) {
        if (gameService.gameAlreadyExists(game)) {
            result.rejectValue("title", "error.game", "Já existe um jogo com o título '" +
                    game.getTitle() + "', plataforma '" + game.getPlatform().getDescription() +
                    "', e mídia '" + game.getFormat().getDescription() + "' cadastrados!");
            result.rejectValue("title", "error.game", "Digite outras informações!");
            logger.warn("Game with title '{}', platform '{}' and format '{}' already exists!",
                    game.getTitle(), game.getPlatform(), game.getFormat());
        }
        if (result.hasErrors()) {
            return newGame(game);
        }
        gameService.saveGame(game);
        attributes.addFlashAttribute("message", "Jogo salvo com sucesso!");
        return new ModelAndView("redirect:/games/new");
    }

    @GetMapping("/search")
    public ModelAndView search() {
        ModelAndView mv = new ModelAndView("games/search-games");
        mv.addObject(new GameFilter());
        mv.addObject("platforms", Platform.values());
        mv.addObject("searchActive", true);
        return mv;
    }

    @PostMapping("/search")
    public ModelAndView searchGames(GameFilter gameFilter, @PageableDefault(size = 20,
            sort = {"title", "platform", "price"}) Pageable pageable) {
        ModelAndView mv = new ModelAndView("games/search-games::gamesTable");
        Page<Game> gamesFound = gameService.filter(gameFilter, pageable);
        mv.addObject("games", gamesFound);
        mv.addObject("numberOfRecords", gamesFound.getTotalElements());
        return mv;
    }

    @GetMapping("/search/{code}")
    public ModelAndView loadGame(@PathVariable Long code) {
        ModelAndView mv = new ModelAndView("games/search-games::gameDetails");
        logger.info("Loading game details with code: {}", code);
        Game game = gamesRepository.findOne(code);
        mv.addObject(game);
        return mv;
    }

    @PatchMapping("/{code}")
    public ModelAndView editGame(@PathVariable Long code) {
        logger.info("Editing game with code: {}", code);
        Game game = gamesRepository.findOne(code);
        return newGame(game);
    }

    @DeleteMapping("/{code}")
    @CacheEvict(value = "games", allEntries = true)
    public String deleteGame(@PathVariable Long code) {
        logger.info("Removing game with code: {}", code);
        gamesRepository.delete(code);
        return "games/search-games::gamesTable";
    }
}
