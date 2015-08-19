package de.fourconnected.gideons.db.rest;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by david on 19.08.15.
 */
public class GlobalGson {
    private static class UnixTimeStampTypeAdaptor implements
            JsonSerializer<Date>, JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement json, Type arg1,
                                JsonDeserializationContext arg2) throws JsonParseException {
            return new Date(json.getAsLong());
        }

        @Override
        public JsonElement serialize(Date date, Type arg1,
                                     JsonSerializationContext arg2) {
            return new JsonPrimitive(date.getTime());
        }
    }

    private static class InstanceHolder {
        private static final GlobalGson INSTANCE = new GlobalGson();
    }

    private Gson gsonInstance;


    private GlobalGson() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Date.class, new UnixTimeStampTypeAdaptor());
        builder.registerTypeAdapter(java.sql.Date.class, new UnixTimeStampTypeAdaptor());
        builder.registerTypeAdapter(java.sql.Timestamp.class, new UnixTimeStampTypeAdaptor());
        this.gsonInstance = builder.create();
    }

    public static final Gson get() {
        return InstanceHolder.INSTANCE.gsonInstance;
    }
}
