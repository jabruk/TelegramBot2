package kz.gru;

import com.google.gson.annotations.SerializedName;

public class Response {


//    @SerializedName(alternate = {
//            "chat_id", "chatId"
//    })
   // @SerializedName(value = "chat_id") =>     @SerializedName("chat_id")
    @SerializedName("chat_id")
    private int chatId;
    private String text;
    private String method;

    @SerializedName("reply_markup")
    private ReplyMarkupKeyboard keyboard;

    private Response(int chatId, String text, String method, ReplyMarkupKeyboard keyboard) {

        this.chatId = chatId;
        this.text = text;
        this.method = method;
        this.keyboard = keyboard;
    }

    public static  ResponseBuilder responseBuilder()
    {
        return new ResponseBuilder();
    }

    public static class ResponseBuilder {

        private int chatId;
        private String text;
        private String method;
        private ReplyMarkupKeyboard keyboard;



        public ResponseBuilder setChatId(int chatId)
        {
            this.chatId = chatId;
            return this;
        }
        public ResponseBuilder setText(String text)
        {
            this.text = text;
            return this;
        }
        public ResponseBuilder setMethod(String method)
        {
            this.method = method;
            return this;
        }
        public ResponseBuilder setReplyMarkUpKeyboard( ReplyMarkupKeyboard keyboard)
        {

            this.keyboard = keyboard;
            return this;
        }
        public Response build()
        {
            return new Response(chatId,text,method,keyboard);
        }
    }
}


