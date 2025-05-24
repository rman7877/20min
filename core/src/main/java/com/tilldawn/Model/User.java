package com.tilldawn.Model;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tilldawn.Model.Enum.Avatar;
import com.tilldawn.Model.Enum.GameTime;
import com.tilldawn.Model.Enum.HeroType;
import com.tilldawn.Model.Enum.WeaponType;

public class User {

    private static ArrayList<User> users = new ArrayList<User>();

    private String username;
    private String password;
    private String securityQuestion;
    private String securityQuestionAnswer;
    private Image avatar;

    private int score;
    private int kills;
    private int longestSurvivalTime;

    private HeroType defualtHeroType;
    private WeaponType defualtWeaponType;
    private GameTime defualtGameTime;

    static {
        addUser(new User("admin", "admin", "What is your favourite color?", "blue", 10, 5, 120));
        // addUser(new User("user", "user", "What is your favourite food?", "pizza", 5,
        // 10, 150));

        // for (int i = 1; i <= 5; i++) {
        // addUser(new User(
        // "user" + i,
        // "password" + i,
        // "What is your favorite hobby?",
        // "hobby" + i,
        // (int) (Math.random() * 100), // Random score
        // (int) (Math.random() * 50), // Random kills
        // (int) (Math.random() * 300) // Random survival time
        // ));
        // }
    }

    public User(String username, String password, String securityQuestion, String securityQuestionAnswer,
            Image avatar) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.avatar = avatar;
        this.score = 0;
        this.kills = 0;
        this.longestSurvivalTime = 0;
        
        this.defualtHeroType = HeroType.SHANA;
        this.defualtWeaponType = WeaponType.REVOLVER;
        this.defualtGameTime = GameTime.TEN;
    }

    public User(String username, String password, String securityQuestion, String securityQuestionAnswer, int score,
            int kills, int longestSurvivalTime) {
        this.username = username;
        this.password = password;
        this.securityQuestion = securityQuestion;
        this.securityQuestionAnswer = securityQuestionAnswer;
        this.avatar = Avatar.getRandomAvatarImage();
        this.score = score;
        this.kills = kills;
        this.longestSurvivalTime = longestSurvivalTime;
        
        this.defualtHeroType = HeroType.SHANA;
        this.defualtWeaponType = WeaponType.REVOLVER;
        this.defualtGameTime = GameTime.TEN;
    }



    public Image getAvatar() {
        return avatar;
    }

    public HeroType getDefualtHeroType() {
        return defualtHeroType;
    }

    public void setDefualtHeroType(HeroType defualtHeroType) {
        this.defualtHeroType = defualtHeroType;
    }

    public WeaponType getDefualtWeaponType() {
        return defualtWeaponType;
    }

    public void setDefualtWeaponType(WeaponType defualtWeaponType) {
        this.defualtWeaponType = defualtWeaponType;
    }

    public GameTime getDefualtGameTime() {
        return defualtGameTime;
    }

    public void setDefualtGameTime(GameTime defualtGameTime) {
        this.defualtGameTime = defualtGameTime;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getLongestSurvivalTime() {
        return longestSurvivalTime;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public String getSecurityQuestion() {
        return securityQuestion;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getSecurityQuestionAnswer() {
        return securityQuestionAnswer;
    }

    public static void addUser(User user) {
        users.add(user);
    }

    public static void removeUser(User user) {
        users.remove(user);
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static boolean isUsernameTaken(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        User user = (User) obj;
        return username.equals(user.username);
    }

}
