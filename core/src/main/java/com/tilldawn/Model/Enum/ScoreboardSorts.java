package com.tilldawn.Model.Enum;

import java.util.ArrayList;

public enum ScoreboardSorts {
    SCORE,
    TIME,
    USERNAME,
    KILL;

    public static String[] getSorts() {
        ArrayList<String> sorts = new ArrayList<>();
        for (ScoreboardSorts sort : ScoreboardSorts.values()) {
            sorts.add(sort.toString());
        }
        return sorts.toArray(new String[0]);
    }



}
