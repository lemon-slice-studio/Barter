package cloud.lemonslice.barter.helper;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public final class JsonHelper
{
    public static JsonObject getJsonFromString(String text)
    {
        Gson g = new Gson();
        return g.fromJson(text, JsonObject.class);
    }
}
