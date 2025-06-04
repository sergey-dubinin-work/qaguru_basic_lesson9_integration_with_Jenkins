package guru.qa.demoqa.helpers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonConverter {

    public static JsonObject convertPlainJsonToJsonObject(String plainJson){
        return JsonParser.parseString(plainJson).getAsJsonObject();
    }

}
