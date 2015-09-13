import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CubePiece {
    private Vector3D pos;
    private Character[] colors = {' ', ' ', ' '};
    public Map<String, int[]> rotation;

    public CubePiece(int argX, int argY, int argZ) {
        pos = new Vector3D(argX, argY, argZ);
        rotation = new HashMap<>();
    }

    public char getColors(int dimension) {
        return colors[dimension];
    }

    public void setColors(int dimension, char color) {
        colors[dimension] = color;
    }

    public Vector3D getPos() {
        return pos;
    }

    public void rotatePieces(int a, int b) {
        int ABuffer = -pos.getAt(a);
        int BBuffer = pos.getAt(b);
        pos.setVal(a, BBuffer);
        pos.setVal(b, ABuffer);
    }

    public void rotateStickers(int a, int b) {
        char colorBuffer = colors[a];
        colors[a] = colors[b];
        colors[b] = colorBuffer;
    }

    public void rotateTransform(String face) {
        int x = -1, y = -1;

        switch (face) {
            case "U":
            case "D'":
                x = 0;
                y = 1;
                break;
            case "D":
            case "U'":
                x = 1;
                y = 0;
                break;
            case "R":
            case "L'":
                x = 2;
                y = 0;
                break;
            case "L":
            case "R'":
                x = 0;
                y = 2;
                break;
            case "F":
            case "B'":
                x = 1;
                y = 2;
                break;
            case "B":
            case "F'":
                x = 2;
                y = 1;
                break;
        }
        if (x == -1 || y == -1) {
            throw new RuntimeException("Wrong case in rotateTransform");
        }
        rotateStickers(x, y);
        rotatePieces(x, y);
    }

    public String toString() {
        return pos + " " + Arrays.toString(colors);
    }


}
