package Common;
import java.io.File;
import java.util.Properties;
import java.io.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONObject;

/**
 * @author Pranjal
 *
 */
public class Configuration {

  /**
     * It will load all the properties files present in config folder.
     *
     *
     */
    public JSONObject readJSOnFile(String path) {
        File folder = new File(".");
        JSONParser parser;
        parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(folder.getCanonicalFile() + "/src/test/java/Common/"+path+".json"));
            JSONObject jsonObject = (JSONObject) obj;
            return jsonObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
