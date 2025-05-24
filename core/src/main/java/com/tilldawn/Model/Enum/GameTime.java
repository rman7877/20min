package com.tilldawn.Model.Enum;

public enum GameTime {
    TWO(2),
    FIVE(5),
    TEN(10),
    TWENTY(20);

    private int time;

    private GameTime(int time) {
        this.time = time;
    }

    public int getTime() {
        return time*60;
    }

    public static String[] getAllGameTime() {
        String[] gameTimes = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            gameTimes[i] = String.valueOf(values()[i].getTime()/60);
        }
        return gameTimes;
    }

    public static GameTime getGameTime(String time) {
        for (GameTime gameTime : values()) {
            if (String.valueOf(gameTime.getTime()/60).equals(time)) {
                return gameTime;
            }
        }
        return null;
    }
}
