package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;
import com.tilldawn.Model.CollisionRect;
import com.tilldawn.Model.Game;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.World;
import com.tilldawn.Model.Enemies.Enemy;
import com.tilldawn.Model.Enemies.Eyebat;
import com.tilldawn.Model.Enemies.TentacleMonster;
import com.tilldawn.Model.Enemies.Tree;

public class WorldController {

    private final static int treeCount = 50;

    private PlayerController playerController;
    private Sprite backgroundSprite;
    private float backgroundX;
    private float backgroundY;

    private World world;

    public WorldController(PlayerController playerController, World world) {
        this.playerController = playerController;
        this.world = world;
        this.backgroundSprite = GameAssetManager.getGameAssetManager().getBackgroundSprite();
        generateRandomTrees();
    }

    public void update() {
        Main.getBatch().draw(backgroundSprite, backgroundSprite.getX(), backgroundSprite.getY());
        updateEnemies();
        // Update world state
    }

    public void updateEnemies() {
        for (Enemy enemy : world.getEnemies()) {
            enemy.getSprite().draw(Main.getBatch());

            Animation<Sprite> enemyAnimation = enemy.getAnimation();
            enemy.update();
            showAnimation(enemyAnimation, enemy);
        }
    }

    private void showAnimation(Animation<Sprite> animation, Enemy enemy) {
        enemy.getSprite().setRegion(animation.getKeyFrame(enemy.getTime()));

        if (!animation.isAnimationFinished(enemy.getTime())) {
            enemy.setTime(enemy.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            enemy.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public void generateTentacleMonster(int count) {
        for (int i = 0; i < count; i++) {
            generateTentacleMonster();
        }
    }

    public void generateTentacleMonster() {
        Sprite tentacleMonsterSprite = GameAssetManager.getGameAssetManager().getTentacleMonsterSprite();
        Animation<Sprite> tentacleMonsterAnimation = GameAssetManager.getGameAssetManager()
                .getTentacleMonsterAnimation();

        float x = (float) (Math.random() * Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 2)
                + Game.getGame().getPlayer().getX();
        float y = (float) (Math.random() * Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 2)
                + Game.getGame().getPlayer().getY();

        tentacleMonsterSprite.setPosition(x, y);
        tentacleMonsterSprite.setSize(50, 50);

        CollisionRect rect = new CollisionRect(x, y, tentacleMonsterSprite.getWidth(),
                tentacleMonsterSprite.getHeight());

        world.addTentacleMonster(new TentacleMonster(tentacleMonsterSprite, tentacleMonsterAnimation, rect));
    }

    public void generateEyebat(int count) {
        for (int i = 0; i < count; i++) {
            generateEyebat();
        }
    }

    public void generateEyebat() {
        Sprite eyebatSprite = GameAssetManager.getGameAssetManager().getEyebatSprite();
        Animation<Sprite> eyebatAnimation = GameAssetManager.getGameAssetManager().getEyebatAnimation();

        float x = (float) (Math.random() * Gdx.graphics.getWidth() - Gdx.graphics.getWidth() / 2)
                + Game.getGame().getPlayer().getX();
        float y = (float) (Math.random() * Gdx.graphics.getHeight() - Gdx.graphics.getHeight() / 2)
                + Game.getGame().getPlayer().getY();

        eyebatSprite.setPosition(x, y);
        eyebatSprite.setSize(40, 25);

        CollisionRect rect = new CollisionRect(x, y, eyebatSprite.getWidth(), eyebatSprite.getHeight());

        world.addEyebat(new Eyebat(eyebatSprite, eyebatAnimation, rect));
    }

    public void generateRandomTrees() {

        for (int i = 0; i < treeCount; i++) {

            Sprite treeSprite = GameAssetManager.getGameAssetManager().getTreeSprite();
            Animation<Sprite> treeAnimation = GameAssetManager.getGameAssetManager().getTreeAnimation();

            int x = (int) (Math.random() * backgroundSprite.getWidth());
            int y = (int) (Math.random() * backgroundSprite.getHeight());

            treeSprite.setPosition(x, y);

            treeSprite.setSize(50, 50); // Set size of the tree sprite

            CollisionRect rect = new CollisionRect(x, y, treeSprite.getWidth(), treeSprite.getHeight());

            world.addTree(new Tree(treeSprite, treeAnimation, rect));
        }
    }

    public World getWorld() {
        return world;
    }

    private float elapsedTime = 0;

}
