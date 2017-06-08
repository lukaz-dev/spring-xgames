package com.xgames.repository;

import com.xgames.model.Format;
import com.xgames.model.Game;
import com.xgames.model.Platform;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GamesRepository extends JpaRepository<Game, Long> {

    List<Game> findByTitleContaining(String title, Pageable pageable);

    List<Game> findDistinctByTitleContainingAndPlatform(String title, Platform platform, Pageable pageable);

    @Query("SELECT DISTINCT g FROM Game g WHERE g.title LIKE CONCAT('%', :title, '%') AND " +
            "g.price >= :minPrice AND g.price <= :maxPrice")
    List<Game> findByTitleAndPrices(@Param("title") String title, @Param("minPrice") BigDecimal min,
                                    @Param("maxPrice") BigDecimal max, Pageable pageable);

    @Query("SELECT DISTINCT g FROM Game g WHERE " +
            "g.title LIKE CONCAT('%', :title, '%') AND g.platform = :platform AND g.price >= :minPrice AND g.price <= :maxPrice")
    List<Game> findByTitleAndPlatformAndPrices(@Param("title") String title, @Param("platform") Platform platform,
                                               @Param("minPrice") BigDecimal minPrice, @Param("maxPrice") BigDecimal maxPrice, Pageable pageable);

    boolean existsByTitleAndPlatformAndFormat(String title, Platform platform, Format format);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM Game g WHERE " +
            "g.title = :title AND g.platform = :platform AND g.format = :format AND g.code <> :code")
    boolean existsOtherGameDiferentFromThisCode(@Param("title") String title, @Param("platform") Platform platform,
                                                @Param("format") Format format, @Param("code") Long code);

}
