import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InvertedSearchEngine implements SearchEngine {
    protected Map<String, List<DocEntry>> index = new HashMap<>();
    protected File folder;

    public InvertedSearchEngine(File folder) {
        this.folder = folder;
        int docsCount = folder.listFiles().length;
        for (int docId = 1; docId <= docsCount; docId++) {
            File docFile = new File(folder.getPath() + "/" + docId);
            String content = readFileContentsOrNull(docFile.getPath());
            String[] words = content.split("\\P{IsAlphabetic}+");

            Map<String, Integer> freqs = new HashMap<>(); // word -> количество
            for (String word : words) {
                freqs.put(word, freqs.getOrDefault(word, 0) + 1);
            }

            for (Map.Entry<String, Integer> wordAndFreq : freqs.entrySet()) {
                String word = wordAndFreq.getKey();
                int freq = wordAndFreq.getValue();
                if (!index.containsKey(word)) {
                    index.put(word, new ArrayList<>());
                }
                index.get(word).add(new DocEntry(docId, freq));
            }
        }

        // сделать сортировку списков в мапе
    }


    @Override
    public List<Snippet> search(String query) {
        List<DocEntry> entries = index.get(query);
        List<Snippet> serp = new ArrayList<>();
        for (DocEntry entry : entries) {
            int docId = entry.getDocId();
            File file = new File(folder.getPath() + "/" + docId);
            String content = readFileContentsOrNull(file.getPath());
            Snippet snippet = new Snippet(docId, content, query, 25);
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