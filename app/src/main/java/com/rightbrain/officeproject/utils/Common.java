package com.rightbrain.officeproject.utils;

import android.view.View;

import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.MyRoomDataBase;

public class Common {

    public static MyRoomDataBase cartDatabase;
    public static CartRepository cartRepositoryy;

    public static boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    public static boolean toggleArrowRightDown(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(90);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

    public static boolean toggleArrowRightDownOpen(View view) {
        if (view.getRotation() == -90) {
            view.animate().setDuration(200).rotation(-0);
            return true;
        } else {
            view.animate().setDuration(200).rotation(-90);
            return false;
        }
    }

    public static boolean toggleArrow(boolean show, View view) {
        return toggleArrow(show, view, true);
    }

    public static boolean toggleArrow(boolean show, View view, boolean delay) {
        if (show) {
            view.animate().setDuration(delay ? 200 : 0).rotation(180);
            return true;
        } else {
            view.animate().setDuration(delay ? 200 : 0).rotation(0);
            return false;
        }
    }
}
