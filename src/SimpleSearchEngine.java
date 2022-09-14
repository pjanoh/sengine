import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleSearchEngine implements SearchEngine {

    protected Map<Integer, String> docsContent = new HashMap<>();
    protected static final int SNIPPET_MARGIN = 35;

    public SimpleSearchEngine(File folder) {
        int docsCount = folder.listFiles().length;
        for (int docId = 1; docId <= docsCount; docId++) {
            File docFile = new File(folder.getPath() + "/" + docId);
            String content = readFileContentsOrNull(docFile.getPath());
            docsContent.put(docId, content);
        }
    }

    public List<Snippet> search(String word) {
        List<Snippet> serp = new ArrayList<>();
        for (Map.Entry<Integer, String> docAndContent : docsContent.entrySet()) {
            int docId = docAndContent.getKey();
            String content = docAndContent.getValue();
            if (!content.contains(word)) {
                continue;
            }
            /*int startIndex = content.indexOf(word);
            String textSnippet = content.substring(
                    Math.max(startIndex - SNIPPET_MARGIN, 0),
                    Math.min(startIndex + word.length() + SNIPPET_MARGIN, content.length())
            );*/
            Snippet snippet = new Snippet(docId, content, word, SNIPPET_MARGIN);
            serp.add(snippet);
        }
        return serp;
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
