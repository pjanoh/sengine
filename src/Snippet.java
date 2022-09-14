public class Snippet {
    protected int docId;
    protected String text;

    public Snippet(int docId, String text) {
        this.docId = docId;
        this.text = text;
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
