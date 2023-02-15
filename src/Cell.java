public class Cell {
    int Ox;
    int Oy;
    CellEntity type;
    CellElement content;
    int visited;

    public Cell(int Ox, int Oy, CellEntity type, CellElement content, int visited) {
        this.Ox = Ox;
        this.Oy = Oy;
        this.type = type;
        this.content = content;
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "Ox=" + Ox +
                ", Oy=" + Oy +
                ", type=" + type +
                ", content=" + content +
                ", visited=" + visited +
                '}';
    }
}
