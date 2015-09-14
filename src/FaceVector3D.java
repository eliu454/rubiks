import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eric on 9/12/15.
 */
public class FaceVector3D extends Vector3D {
    private int[] iter = new int[2];
    private int dimension = -500;
    private List<int[]> piecePos = new ArrayList<>();

    public FaceVector3D(int x, int y, int z) {
        super(x, y, z);
    }

    public void setDimension(int u){
        dimension = u;
    }

    public int getDimension() {
        int dimensionBuffer = dimension;
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            if (getVal()[i] != 0) {
                dimension = i;
                counter++;
            }
        }
        if (counter > 1) {
            dimension = dimensionBuffer;
            System.out.println(this.toString());
            throw new RuntimeException("getDimension should only be used on a face vector");
        }
        return dimension;
    }


    public int[] getIter() {
        if (dimension == -500) {
            throw new RuntimeException("Used getIter without first using getDimension");
        }
        if (dimension == 0) {
            iter[0] = 2;
            iter[1] = 1;
        } else if (dimension == 1) {
            iter[0] = 0;
            iter[1] = 2;
        } else {
            iter[0] = 0;
            iter[1] = 1;
        }
        return iter;
    }

    public List<int[]> getPiecePosOnFace() {
        if (piecePos.isEmpty()) {
            getDimension();
            getIter();
            for (int a = -1; a <= 1; a++) {
                for (int b = -1; b <= 1; b++) {
                    int[] pos = Arrays.copyOf(getVal(), 3);
                    pos[iter[0]] = a;
                    pos[iter[1]] = b;
                    piecePos.add(pos);
                }
            }
        }
        return piecePos;
    }
}
