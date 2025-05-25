package com.tilldawn.Model;

import java.util.ArrayList;

import com.tilldawn.Model.Enemies.Enemy;
import com.tilldawn.Model.Enemies.Eyebat;
import com.tilldawn.Model.Enemies.TentacleMonster;
import com.tilldawn.Model.Enemies.Tree;

public class World {

    private static World world;

    private ArrayList<Enemy> enemies;
    private ArrayList<Tree> trees;
    private ArrayList<TentacleMonster> tentacleMonsters;
    private ArrayList<Eyebat> eyebats;


    public World() {
        this.trees = new ArrayList<>();
        this.tentacleMonsters = new ArrayList<>();
        this.eyebats = new ArrayList<>();
        this.enemies = new ArrayList<>();
        
    }

    public World getWorld() {
        if (world == null) {
            world = new World();
        }
        return world;
    }

    public ArrayList<Tree> getTrees() {
        return trees;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
    
    public void addTree(Tree tree) {
        trees.add(tree);
        enemies.add(tree);
    }

    public void addTentacleMonster(TentacleMonster tentacleMonster) {
        tentacleMonsters.add(tentacleMonster);
        enemies.add(tentacleMonster);
    }

    public void addEyebat(Eyebat eyebat) {
        eyebats.add(eyebat);
        enemies.add(eyebat);
    }
    
}
