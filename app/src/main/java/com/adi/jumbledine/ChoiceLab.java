package com.adi.jumbledine;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Adi on 2016-12-30.
 */
public class ChoiceLab {
    private static ChoiceLab sChoiceLab;
    private List<Choice> mChoices;

    public static ChoiceLab get(Context context) {
        if (sChoiceLab == null) {
            sChoiceLab = new ChoiceLab(context);
        }
        return sChoiceLab;
    }

    private ChoiceLab(Context context) {
        mChoices = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Choice choice = new Choice();
            choice.setType("Choice #" + i);
            choice.setTitle("Restaurant #" + i);
            choice.setDistance(i + "km");
            choice.setAddress("Street" + i);
            mChoices.add(choice);
        }

    }

    public List<Choice> getChoices() {
        return mChoices;
    }
    public Choice getChoice(UUID id) {
        for (Choice choice : mChoices) {
            if (choice.getId().equals(id)) {
                return choice;
            }
        }
        return null;
    }

    public void addChoice(Choice c) {
        mChoices.add(c);
    }


}
