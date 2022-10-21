package kz.gru;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;


// Handler value: example.HandlerStream
public class MainHandler implements RequestStreamHandler {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException
    {

        LambdaLogger lambdaLogger = context.getLogger();

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


        JsonObject jsonObject = gson.fromJson(bufferedReader, JsonObject.class);

        JsonObject bodyJson = gson.fromJson( jsonObject.get("body").getAsString(), JsonObject.class);
        int id;
        String text = "gde text";
        if(bodyJson.has("message"))
        {
            JsonObject message = bodyJson.get("message").getAsJsonObject();
            id = message.get("chat").getAsJsonObject().get("id").getAsInt();

            if(message.has("text"))
            {
                text = "Hi, you sent: " + message.get("text").getAsString();
            }
        } else
        {
            lambdaLogger.log("LOG: chat ID not found");// add body id in future
            return;
        }



        ReplyMarkupKeyboard replyMarkupKeyboard = new ReplyMarkupKeyboard(buttonsSet());
        Response response = Response
                .responseBuilder()
                .setText(text)
                .setChatId(id)
                .setMethod("sendMessage")
                .setReplyMarkUpKeyboard(replyMarkupKeyboard)
                .build();

        lambdaLogger.log("RESPONSE: " + gson.toJson(response));
        String stringResponse ="{\"statusCode\": 200,\n" +
                "\"body\": "+gson.toJson(response)+",\n" +
                "\"headers\": {\n" +
                "    \"content-type\": \"application/json\"\n" +
                "  }}";

        lambdaLogger.log("JSON: " + jsonObject.toString());
        outputStream.write(stringResponse.getBytes(StandardCharsets.UTF_8));

        lambdaLogger.log("LOG:" + stringResponse);
        outputStream.flush();
        outputStream.close();
    }







    private List<List<Button>> buttonsSet() {
        List<List<Button>> buttonsFirstRow = new ArrayList<>();

        buttonsFirstRow.add(new ArrayList<>());
        buttonsFirstRow.get(0).add(new Button("Поесть"));
        buttonsFirstRow.get(0).add(new Button("Прогуляться"));

        buttonsFirstRow.add(new ArrayList<>());
        buttonsFirstRow.get(1).add(new Button("Повеселиться"));
        buttonsFirstRow.get(1).add(new Button("Посетить"));
        return buttonsFirstRow;
    }
}