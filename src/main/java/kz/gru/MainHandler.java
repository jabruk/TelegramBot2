package kz.gru;
import ReplyMarkupKeyboard.MainReplyMarkupKeyboard;
import ReplyMarkupKeyboard.ReplyMarkupKeyboard;
import ReplyMarkupKeyboard.DetermineKeyBoard;
import Response.Response;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import Buttons.Button;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Stream;


// Handler value: example.HandlerStream
public class MainHandler implements RequestStreamHandler {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger lambdaLogger = context.getLogger();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        Map<String,String> map;
        JsonObject jsonObject = gson.fromJson(bufferedReader, JsonObject.class);
        JsonObject bodyJson = gson.fromJson( jsonObject.get("body").getAsString(), JsonObject.class);
        ReplyMarkupKeyboard replyMarkupKeyboard = null;

        map = byStream("TestContainer.txt");
        lambdaLogger.log("MAP: " + map);// add body id in future

        int id;
        String text = "gde text";
        if(bodyJson.has("message")) {
            JsonObject message = bodyJson.get("message").getAsJsonObject();
            id = message.get("chat").getAsJsonObject().get("id").getAsInt();
            boolean hasIncomeText = message.has("text");
            if(hasIncomeText) {
                replyMarkupKeyboard = DetermineKeyBoard.findKeyboard(map,message.get("text").getAsString());
                text = replyMarkupKeyboard.getText();
            }
        } else {
            lambdaLogger.log("LOG: chat ID not found");// add body id in future
            return;
        }

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

    private boolean wasButtonPressed(String text, ReplyMarkupKeyboard replyMarkupKeyboard) {
        Button hasButton = new Button(text);
        for(List<Button> list : replyMarkupKeyboard.getKeyboard()) {
            if(list.contains(hasButton)) return true;
        }
        return false;
    }

    private static Map<String, String> byStream(String filePath) {
        Map<String, String> map = new HashMap<>();
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            lines.filter(line -> line.contains(":"))
                    .forEach(line -> {
                        String[] keyValuePair = line.split(":", 2);
                        String key = keyValuePair[0];
                        String value = keyValuePair[1];
                        map.put(key, value);

                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

}