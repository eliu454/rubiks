/**
 * Created by eric on 9/10/15.
 */
public class Vector3D {
    private int[] val = new int[3];

    public Vector3D() {
    }

    public Vector3D(int argX, int argY, int argZ) {
        val[0] = argX;
        val[1] = argY;
        val[2] = argZ;
    }

    public int getAt(int u) {
        return val[u];
    }

    public void setVal(int u, int value) {
        val[u] = value;
    }

    public int dot(Vector3D u) {
        int dot_product = 0;
        for (int i = 0; i < val.length; i++) {
            dot_product += u.getAt(i) * this.getAt(i);
        }
        return dot_product;
    }

    public int dot(int x, int y, int z) {
        return this.getAt(0) * x + this.getAt(1) * y + this.getAt(2) * z;
    }

    public int[] getVal() {
        return val;
    }

    public String toString() {
        return " <" + val[0] + ", " + val[1] + ", " + val[2] + "> ";
    }

    public static Vector3D copyVector(Vector3D argVector) {
        Vector3D vec = new Vector3D();
        for (int i = 0; i < 3; i++) {
            vec.setVal(i, argVector.getAt(i));
            vec.val[i] = argVector.getAt(i);
        }
        return vec;
    }


}
