import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SearchEngine engine = new SearchEngine(new File("docs"));

        List<Snippet> serp = engine.search("задание");
        for (Snippet snippet : serp) {
            System.out.println(snippet);
        }
    }
}
