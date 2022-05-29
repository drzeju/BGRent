package com.example.BGRent;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class GameEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameId;

    @Column(name = "gameName", nullable = false)
    private String gameName;

    @Column(name="category", nullable = false)
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "categoryId", referencedColumnName = "categoryId"),
//            @JoinColumn(name = "categotyName", referencedColumnName = "categoryName")
    })
    private CategoryEntity category;

    @Column(name = "gameDescription")
    private String gameDescription;

    @Column(name = "gamePrice", nullable = false)
    private Integer gamePrice;

    public GameEntity() { }

    public GameEntity(long gameId,CategoryEntity category, String gameName, String gameDescription, Integer gamePrice) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.category = category;
        this.gameDescription = gameDescription;
        this.gamePrice = gamePrice;
    }

    public long getId() {
        return this.gameId;
    }

    public void setId(long gameId) {
        this.gameId = gameId;
    }

    public String getName() {
        return this.gameName;
    }

    public void setName(String gameName) {
        this.gameName = gameName;
    }

    //////////////////////////////////////////////////////

    public CategoryEntity getCategory() {
        return this.category;
    }
    //////////////////////////////////////////////////////

    public String getDescription() {
        return this.gameDescription;
    }

    public void setDescription(String gameDescription) {
        this.gameDescription = gameDescription;
    }

    public Integer getPrice() {
        return this.gamePrice;
    }

    public void setPrice(Integer gamePrice) {
        this.gamePrice = gamePrice;
    }
}
