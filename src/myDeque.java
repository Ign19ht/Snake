import java.util.ArrayDeque;

class myDeque {

    private ArrayDeque<Vector> vectors = new ArrayDeque<>();

    void add(int x, int y) {
        vectors.add(new Vector(x, y));
        if (vectors.size() > 8) {
            vectors.pop();
        }
    }

    Vector pop() {
        return vectors.getFirst();
    }

}
