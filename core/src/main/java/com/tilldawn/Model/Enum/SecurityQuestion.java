package com.tilldawn.Model.Enum;

public enum SecurityQuestion {
    FIRST_PET_NAME("What was the name of your first pet?"),
    MOTHER_MAIDEN_NAME("What is your mother's maiden name?"),
    FAVORITE_TEACHER("Who was your favorite teacher?"),
    BIRTH_CITY("In what city were you born?"),
    FIRST_SCHOOL_NAME("What was the name of your first school?"),
    FAVORITE_BOOK("What is your favorite book?"),
    FAVORITE_FOOD("What is your favorite food?"),
    CHILDHOOD_NICKNAME("What was your childhood nickname?"),
    FAVORITE_MOVIE("What is your favorite movie?"),
    FIRST_CAR_MODEL("What was the model of your first car?");
    
    private String question;

    SecurityQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public static String[] getAllQuestions() {
        String[] questions = new String[SecurityQuestion.values().length];
        for (int i = 0; i < SecurityQuestion.values().length; i++) {
            questions[i] = SecurityQuestion.values()[i].getQuestion();
        }
        return questions;
    }
}
