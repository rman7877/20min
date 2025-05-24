package com.tilldawn.Control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.tilldawn.Main;
import com.tilldawn.Model.GameAssetManager;
import com.tilldawn.Model.Player;

public class PlayerController {

    private Player player;
    private boolean rightPressed = false;

    public PlayerController(Player player) {
        this.player = player;
    }

    public void update() {
        player.getSprite().draw(Main.getBatch());

        if (player.isPlayerIdle())
            idleAnimation();
        else
            runAnimation();

    }


    public void handlePlayerMovement(int i)
    {
        if( i == Keys.D || i == Keys.W)
            rightPressed = true;
        else if (i == Keys.A || i == Keys.S)
            rightPressed = false;
        player.setPlayerIdle(false);
    }

    public void handlePlayerIdle()
    {
        player.setPlayerIdle(true);
    }

    public void idleAnimation() {
        showAnimation(player.getHeroType().getIdleAnimation());
    }

    public void runAnimation() {
        Animation<Sprite> animation = rightPressed ? player.getHeroType().getRunRightAnimation() : player.getHeroType().getRunLeftAnimation();
        showAnimation(animation);
    }

    public void showAnimation(Animation<Sprite> animation) {
        player.getSprite().setRegion(animation.getKeyFrame(player.getTime()));

        if (!animation.isAnimationFinished(player.getTime())) {
            player.setTime(player.getTime() + Gdx.graphics.getDeltaTime());
        } else {
            player.setTime(0);
        }

        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    public Player getPlayer() {
        return player;
    }

}
