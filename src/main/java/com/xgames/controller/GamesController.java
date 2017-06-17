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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

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
        ModelAndView mv = new ModelAndView("/games/new-game");
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
    public ModelAndView search(GameFilter gameFilter) {
        ModelAndView mv = new ModelAndView("/games/search-games");
        mv.addObject("platforms", Platform.values());
        mv.addObject("searchActive", true);
        return mv;
    }

    @PostMapping("/search")
    public ModelAndView searchGames(GameFilter gameFilter) {
        System.out.println(gameFilter);
        ModelAndView mv = new ModelAndView("/games/search-games::gamesTable");
        List<Game> gamesFound = gameService.filter(gameFilter);
        mv.addObject("games", gamesFound);
        mv.addObject("numberOfRecords", gamesFound.size());
        return mv;
    }

    @GetMapping("/search/{code}")
    public String loadGame(@PathVariable Long code, Model model) {
        logger.info("Loading game details with code: {}", code);
        Game game = gamesRepository.findOne(code);
        model.addAttribute(game);
        return "/games/search-games::gameDetails";
    }

    @PatchMapping("/{code}")
    public ModelAndView editGame(@PathVariable Long code) {
        logger.info("Editing game with code: {}", code);
        Game game = gamesRepository.findOne(code);
        return newGame(game);
    }

    @DeleteMapping("/{code}")
    @CacheEvict(value = "games", allEntries = true)
    public ModelAndView deleteGame(@PathVariable Long code) {
        logger.info("Removing game with code: {}", code);
        ModelAndView mv = new ModelAndView("/games/search-games::gamesTable");
        gamesRepository.delete(code);
        return mv;
    }
}
