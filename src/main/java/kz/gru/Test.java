package kz.gru;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<List<Button>> buttonsFirstRow = new ArrayList<>();

        buttonsFirstRow.add(new ArrayList<>());
        buttonsFirstRow.get(0).add(new Button("Поесть"));
        buttonsFirstRow.get(0).add(new Button("Прогуляться"));

        buttonsFirstRow.add(new ArrayList<>());
        buttonsFirstRow.get(1).add(new Button("Повеселиться"));
        buttonsFirstRow.get(1).add(new Button("Посетить"));

        ReplyMarkupKeyboard replyMarkupKeyboard = new ReplyMarkupKeyboard(buttonsFirstRow);


        Response response = Response
                .responseBuilder()
                .setText("")
                .setChatId(820844585)
                .setMethod("sendMessage")
                .setReplyMarkUpKeyboard(replyMarkupKeyboard)
                .build();

        String a = gson.toJson(response);
    }



    public static void sendToTelegram(String chat_id, String text)   {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";


        String apiToken = "5461879107:AAHiaq2tvTAV1z2gAAzIuwCWrYPvNrQSc7E";

    }



}
