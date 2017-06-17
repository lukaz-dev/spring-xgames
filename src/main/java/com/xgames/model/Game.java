package com.xgames.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue
    private Long code;

    @NotBlank
    @Length(max = 50, min = 1)
    private String title;

    @NotBlank
    @Length(max = 50, min = 1)
    private String publisher;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Category category;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    private Platform platform;

    @NotNull
    @DecimalMax("500.0")
    @DecimalMin("3.0")
    @Column(precision = 10, scale = 1)
    private BigDecimal size;

    @NotNull
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @NotNull
    @DecimalMax("999.90")
    @DecimalMin("9.90")
    @Column(precision = 10, scale = 2)
    @NumberFormat(pattern = "#,##0.00")
    private BigDecimal price;

    @NotNull
    @Max(100000)
    @Min(50)
    private Integer stock;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Format format;

    @NotBlank
    @Length(max = 255, min = 5)
    private String description;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public BigDecimal getSize() {
        return size;
    }

    public void setSize(BigDecimal size) {
        this.size = size;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        return code != null ? code.equals(game.code) : game.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Game {" +
                "code=" + code +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", category=" + category +
                ", platform=" + platform +
                ", size=" + size +
                ", releaseDate=" + releaseDate +
                ", price=" + price +
                ", stock=" + stock +
                ", format=" + format +
                ", description='" + description + '\'' +
                '}';
    }
}
