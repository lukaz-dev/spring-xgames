package com.xgames.model.filter;

import com.xgames.model.Platform;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

public class GameFilter {

    private String title;
    private Platform platform;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal minPrice;

    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal maxPrice;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() {
        return "GameFilter{" +
                "title='" + title + '\'' +
                ", platform=" + platform +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
