package com.tilldawn.Model;

import java.util.ArrayList;

import com.tilldawn.Model.Enemies.Elder;
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

    private ArrayList<Seed> seeds;

    private Elder elder;

    private ArrayList<Bullet> eyebatBullets;

    public World() {
        this.trees = new ArrayList<>();
        this.tentacleMonsters = new ArrayList<>();
        this.eyebats = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.eyebatBullets = new ArrayList<>();
        this.seeds = new ArrayList<>();
        elder = null;
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

    public ArrayList<TentacleMonster> getTentacleMonsters() {
        return tentacleMonsters;
    }

    public ArrayList<Eyebat> getEyebats() {
        return eyebats;
    }

    public ArrayList<Bullet> getEyebatBullets() {
        return eyebatBullets;
    }

    public ArrayList<Seed> getSeeds() {
        return seeds;
    }

    public void addSeed(Seed seed) {
        seeds.add(seed);
    }

    public void removeSeed(Seed seed) {
        seeds.remove(seed);
    }

    public void addEyebatBullet(Bullet bullet) {
        eyebatBullets.add(bullet);
    }

    public Elder getElder() {
        return elder;
    }

    public void setElder(Elder elder) {
        this.elder = elder;
        enemies.add(elder);
    }

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
        if (enemy instanceof Tree) {
            trees.remove(enemy);
        } else if (enemy instanceof TentacleMonster) {
            tentacleMonsters.remove(enemy);
        } else if (enemy instanceof Eyebat) {
            eyebats.remove(enemy);
        } else if (enemy instanceof Elder) {
            elder = null;
        }
    }

}
