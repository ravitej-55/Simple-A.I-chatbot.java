import org.json.JSONArray;
import org.json.JSONObject;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Chatbot {
    private List<Intent> intents = new ArrayList<>();

    public Chatbot(String intentsPath) throws Exception {
        String content = new String(Files.readAllBytes(Paths.get(intentsPath)));
        JSONObject root = new JSONObject(content);
        JSONArray arr = root.getJSONArray("intents");
        for (int i = 0; i < arr.length(); i++) {
            JSONObject obj = arr.getJSONObject(i);
            Intent intent = new Intent(
                obj.getString("tag"),
                obj.getJSONArray("patterns").toList(),
                obj.getJSONArray("responses").toList()
            );
            intents.add(intent);
        }
    }

    public String getResponse(String message) {
        message = message.toLowerCase();
        for (Intent intent : intents) {
            for (Object p : intent.patterns) {
                String pattern = p.toString().toLowerCase();
                if (message.contains(pattern)) {
                    List<Object> responses = intent.responses;
                    return responses.get(new Random().nextInt(responses.size())).toString();
                }
            }
        }
        return "Sorry, I didn't understand that.";
    }

    static class Intent {
        String tag;
        List<Object> patterns;
        List<Object> responses;

        public Intent(String tag, List<Object> patterns, List<Object> responses) {
            this.tag = tag;
            this.patterns = patterns;
            this.responses = responses;
        }
    }
}
