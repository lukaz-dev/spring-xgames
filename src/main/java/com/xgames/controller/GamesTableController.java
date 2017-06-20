package com.xgames.controller;

import com.xgames.model.Game;
import com.xgames.controller.pagination.Pager;
import com.xgames.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GamesTableController {

    private static final int BUTTONS_TO_SHOW = 5;
    private static final int INITIAL_PAGE = 0;
    private static final int INITIAL_PAGE_SIZE = 5;
    private static final int[] PAGE_SIZES = {5, 10, 20};

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    @Cacheable("games")
    public ModelAndView listGames(@PageableDefault(size = INITIAL_PAGE_SIZE,
            sort = {"title", "platform", "price"}) Pageable pageable) {
        ModelAndView mv = new ModelAndView("games/table-games");

        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber() < 1 ? INITIAL_PAGE : pageable.getPageNumber() - 1;

        Page<Game> games = gameService.findAllPageable(new PageRequest(pageNumber, pageSize, pageable.getSort()));
        Pager pager = new Pager(games.getTotalPages(), games.getNumber(), BUTTONS_TO_SHOW);

        mv.addObject("games", games);
        mv.addObject("selectedPageSize", pageSize);
        mv.addObject("pageSizes", PAGE_SIZES);
        mv.addObject("pager", pager);
        mv.addObject("totalOfRecords", games.getTotalElements());
        mv.addObject("tableActive", true);
        return mv;
    }
}
