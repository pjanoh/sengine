public class DocEntry {
    protected int docId;
    protected int count;

    public DocEntry(int docId, int count) {
        this.docId = docId;
        this.count = count;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
