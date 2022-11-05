package kz.gru;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


// Handler value: example.HandlerStream
public class MainHandler implements RequestStreamHandler {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        LambdaLogger lambdaLogger = context.getLogger();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        JsonObject jsonObject = gson.fromJson(bufferedReader, JsonObject.class);
        JsonObject bodyJson = gson.fromJson( jsonObject.get("body").getAsString(), JsonObject.class);



        Response response = createResponse(lambdaLogger,bodyJson);
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

    private static Map<String, String> getKeyboardsByTextFromTextFile(String filePath) {
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

    private static Map<String, List<String>> getButtonsNameFromTextFile(String filePath) throws IOException {
//        return Files.readAllLines(Paths.get(file)).stream()
//                .map(line -> line.split(":"))
//                .collect(
//                        groupingBy(
//                                array -> array[0].trim(),
//                                mapping(array -> array[1].trim(),
//                                        toList())
//                        )
//                );
        Map<String, List<String>> map = new HashMap<>();
        Stream<String> lines = Files.lines(Paths.get(filePath));
            lines.filter(line -> line.contains(":"))
                    .forEach(line -> {
                        String[] keyValuePair = line.split(":", 2);
                        String key = keyValuePair[0];
                        List<String> value = List.of(keyValuePair[1].split(","));
                        map.put(key, value);

                    });

        return map;

    }

    private static Response createResponse(LambdaLogger lambdaLogger, JsonObject bodyJson) throws IOException {

        ReplyMarkupKeyboard replyMarkupKeyboard = null;
        Map<String,String> mapKeyboards;
        Map<String,List<String>> mapButtons;
        mapKeyboards = getKeyboardsByTextFromTextFile("TestContainer.txt");
        mapButtons = getButtonsNameFromTextFile("ButtonList.txt");
        lambdaLogger.log("MAP: " + mapButtons);
        int id;
        String text = "gde text";
        if(bodyJson.has("message")) {

            JsonObject message = bodyJson.get("message").getAsJsonObject();
            id = message.get("chat").getAsJsonObject().get("id").getAsInt();
            boolean hasIncomeText = message.has("text");

            if(hasIncomeText) {
                    replyMarkupKeyboard = DetermineKeyBoard.findKeyboard(mapKeyboards, mapButtons, message.get("text").getAsString());
                    text = mapKeyboards.containsKey(message.get("text").getAsString()) ? replyMarkupKeyboard.getText() : "Братиш, орысша сойлеш, саипал, попробуй еще раз";
            }

        } else {
            lambdaLogger.log("LOG: chat ID not found");// add body id in future
            return null;
        }
        lambdaLogger.log("KeyBoard: " + replyMarkupKeyboard.getKeyboard());
        Response response = Response
                .responseBuilder()
                .setText(text)
                .setChatId(id)
                .setMethod("sendMessage")
                .setReplyMarkUpKeyboard(replyMarkupKeyboard)
                .build();

        lambdaLogger.log("RESPONSE: " + gson.toJson(response));
        return response;
    }

}