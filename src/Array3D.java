
public class Array3D<T> {
    private Object[][][] arr;
    private int beg_index;
    private int end_index;

    public Array3D(int arg_beg_index, int arg_end_index) {
        this.beg_index = arg_beg_index;
        this.end_index = arg_end_index;

        int len = end_index - beg_index + 1;
        arr = new Object[len][len][len];
    }

    public T getAtPos(int[] arr){
        if(arr.length!=3){
            throw new RuntimeException("Array length must be 3");
        }
        return getAtPos(arr[0], arr[1], arr[2]);
    }
    public T getAtPos(int x, int y, int z) {
        final T t = (T) arr[x - beg_index][y - beg_index][z - beg_index];
        return t;
    }

    public void setAtPos(int x, int y, int z, T value) {
        arr[x - beg_index][y - beg_index][z - beg_index] = value;
    }
    public void setAtPos(int[] arr, T value){
        setAtPos(arr[0],arr[1],arr[2],value);
    }


}