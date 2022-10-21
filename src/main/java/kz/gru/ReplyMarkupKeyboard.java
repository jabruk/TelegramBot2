package kz.gru;

import java.util.ArrayList;
import java.util.List;

public class ReplyMarkupKeyboard {



    private String text;
//    private List<ButtonsRow> keyboard = new ArrayList<>();
//
//    public  ReplyMarkupKeyboard(List<ButtonsRow> markUpKeyboard) {
//        text = "text";
//        this.keyboard = markUpKeyboard;
//    }




    private List<List<Button>> keyboard = new ArrayList<>();

    public  ReplyMarkupKeyboard(List<List<Button>> markUpKeyboard) {
        text = "";
        this.keyboard = markUpKeyboard;
    }



}