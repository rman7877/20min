package com.tilldawn.Model.Enum;

import com.tilldawn.Model.Player;
import com.tilldawn.Model.Weapon;

public enum Ability {
    VITALITY("Increase your maximum health by 1."),
    DAMAGER("Increase your weapon damage by 25% for 10 seconds."),
    PROCREASE("Increase your weapon projectile count by 1."),
    AMOCREASE("Increase your weapon maximum ammo by 5."),
    SPEEDY("Increase your movement speed by 100% for 10 seconds.");

    private String description;

    Ability(String description) {
        this.description = description;
    }

    private void setVitility(Player player) {
        player.setMaxHealth(player.getMaxHealth() + 1);
    }

    private void setDamager(Weapon weapon) {
        weapon.setDamage((int) (weapon.getDamage() * 1.25f));
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    weapon.setDamage((int) (weapon.getDamage() / 1.25f));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    private void setProcrease(Weapon weapon) {
        weapon.setProjectile(weapon.getProjectile() + 1);
    }

    private void setAmocrease(Weapon weapon) {
        weapon.setMaxAmmo(weapon.getMaxAmmo()+5);
    }

    private void setSpeedy(Player player) {
        player.setSpeed(player.getSpeed() * 2);
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                player.setSpeed(player.getSpeed() / 2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void setAbility(Player player, Weapon weapon) {
        switch (this) {
            case VITALITY:
                setVitility(player);
                break;
            case DAMAGER:
                setDamager(weapon);
                break;
            case PROCREASE:
                setProcrease(weapon);
                break;
            case AMOCREASE:
                setAmocrease(weapon);
                break;
            case SPEEDY:
                setSpeedy(player);
                break;

        }
        player.addAbility(this);

    }

    public String getDescription() {
        return description;
    }

}
