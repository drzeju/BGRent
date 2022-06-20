package com.example.BGRent;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class CategoryEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoryId;

    @Column(name = "categoryName", nullable = false)
    private String categoryName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="category")
//    private List<GameEntity> games = new ArrayList<>();
    private Set<GameEntity> games;
//            = new HashSet<GameEntity>();

    public CategoryEntity() { }

    public CategoryEntity(long categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // <------------------------------------------------------------------------------------------
//    public List<GameEntity> getGames(){
//        return games;
//    }
//
//    public void setGames(List<GameEntity> games){
//        this.games = games;
//    }

}
