package com.tilldawn.Model;

import java.util.ArrayList;

import com.tilldawn.Model.Enemies.Enemy;
import com.tilldawn.Model.Enemies.Tree;

public class World {

    private static World world;

    private ArrayList<Enemy> enemies;
    private ArrayList<Tree> trees;


    public World() {
        this.trees = new ArrayList<>();
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
    
}
