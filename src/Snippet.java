public class Snippet {
    protected int docId;
    protected String text;

    public Snippet(int docId, String text) {
        this.docId = docId;
        this.text = text;
    }

    public Snippet(int docId, String fullContent, String keyWord, int margin) {
        int startIndex = fullContent.indexOf(keyWord);
        String textSnippet = fullContent.substring(
                Math.max(startIndex - margin, 0),
                Math.min(startIndex + keyWord.length() + margin, fullContent.length())
        );
        this.docId = docId;
        this.text = textSnippet;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "-".repeat(10) + "\n" +
                docId + "\n" +
                "...\n" + text + "\n..." +
                "\n\n";
    }
}
