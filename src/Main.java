import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        SearchEngine engine = new InvertedSearchEngine(new File("docs"));

        List<Snippet> serp = engine.search("Задание");
        for (Snippet snippet : serp) {
            System.out.println(snippet);
        }
    }
}
