package json;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    ArrayList<Tuple<String, Json>> jsonPairs;

    public JsonObject(JsonPair... jsonPairs) {
        this.jsonPairs = new ArrayList<Tuple<String, Json>>();
        this.jsonPairs.addAll(Arrays.asList(jsonPairs));
    }

    public static void main(String[] args) {
        JsonPair jSurname = new JsonPair("surname", new JsonString("Nik"));
        JsonPair jActive = new JsonPair("active", new JsonBoolean(true));
        JsonObject jsonObject = new JsonObject(jSurname, jActive);
        System.out.println(jsonObject.toJson());
    }

    @Override
    public String toJson() {
        StringBuilder toJsonString = new StringBuilder("{");
        for (Tuple<String, Json> jsonPair :
                jsonPairs) {
            if (!toJsonString.toString().equals("{")) {
                toJsonString.append(", ");
            }
            toJsonString.append("'").append(jsonPair.key).append("': ").append(jsonPair.value.toJson());
        }
        return toJsonString + "}";
    }

    public void add(JsonPair jsonPair) {
        jsonPairs.add(jsonPair);
    }

    public boolean contains(String name) {
        for (Tuple<String, Json> jsonPair : jsonPairs) {
            if (jsonPair.key.equals(name)) {
                return true;
            }
        }

        return false;
    }

    public Json find(String name) {
        for (Tuple<String, Json> jsonPair : jsonPairs) {
            if (jsonPair.key.equals(name)) {
                return jsonPair.value;
            }
        }

        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject result = new JsonObject();
        for (String name:
             names) {
            if(contains(name)) {
                result.add(new JsonPair(name, find(name)));
            }
        }
        return null;
    }
}
