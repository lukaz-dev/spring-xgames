package com.xgames.controller;

import com.xgames.repository.GamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TableController {

    @Autowired
    private GamesRepository gamesRepository;

    @GetMapping("/games")
    public ModelAndView listGames() {
        ModelAndView mv = new ModelAndView("/games/table-games");
        // limitar os registros, paginação (Pageable)
        mv.addObject("games", gamesRepository.findAll());
        mv.addObject("tableActive", true);

        return mv;
    }
}
