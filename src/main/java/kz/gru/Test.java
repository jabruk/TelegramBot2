package kz.gru;


public class Test {

    public static void main(String[] args) {
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//
//
//        ReplyMarkupKeyboard replyMarkupKeyboard = new ReplyMarkupKeyboard();
//
//
//        Response response = Response
//                .responseBuilder()
//                .setText("")
//                .setChatId(820844585)
//                .setMethod("sendMessage")
//                .setReplyMarkUpKeyboard(replyMarkupKeyboard)
//                .build();
//
//        String a = gson.toJson(response);
    }



    public static void sendToTelegram(String chat_id, String text)   {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";


        String apiToken = "5461879107:AAHiaq2tvTAV1z2gAAzIuwCWrYPvNrQSc7E";

    }



}
