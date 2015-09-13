import java.util.*;

public class Cube {
    private Array3D<CubePiece> rubiksCube = new Array3D<>(-1, 1);
    public static final Character[] faceColors = {'W', 'Y', 'R', 'O', 'B', 'G'};
    public static final Character[] faces = {'F', 'B', 'R', 'L', 'U', 'D'};
    public static Map<Character, FaceVector3D> faceVecMap = new HashMap<>();

    public Cube() {
        initFaceVecMap();
        initPieces();
        initStickers();

    }

    //generates all the vectors for the pieces
    private void initPieces() {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    CubePiece currCubePiece = new CubePiece(x, y, z);
                    rubiksCube.setAtPos(x, y, z, currCubePiece);
                }
            }
        }
    }

    //loops through each face, and inits the stickers on that side
    private void initStickers() {
        for (int i = 0; i < faces.length; i++) {
            initStickersOnFace(faceVecMap.get(faces[i]), faceColors[i]);
        }

    }


    //returns 2D matrix of all the stickers on a side
    public char[][] getStickersOnFace(FaceVector3D faceVec) {
        char[][] mat = new char[3][3];
        for (int[] pos : faceVec.getPiecePosOnFace()) {
            int[] iter = faceVec.getIter();
            int dimension = faceVec.getDimension();
            CubePiece currPiece = rubiksCube.getAtPos(pos);
            mat[pos[iter[0]] + 1][pos[iter[1]] + 1] = currPiece.getColors(dimension);
        }
        return mat;
    }

    //fills all the cube pieces on a side with a certain color
    private void initStickersOnFace(FaceVector3D faceVec, Character color) {
        for (int[] pos : faceVec.getPiecePosOnFace()) {
            int dimension = faceVec.getDimension();
            CubePiece currPiece = rubiksCube.getAtPos(pos[0], pos[1], pos[2]);
            currPiece.setColors(dimension, color);
        }
    }

    //generates the values for faceVecMap, which maps the following
    // 'F':<1,0,0> 'B':<-1,0,0>
    // 'R:'<0,1,0> 'L':<0,-1,0>
    // 'U:'<0,0,1> 'D':<0,0,-1>
    private void initFaceVecMap() {
        //loops through each dimension, setting the current dimension to 1 and -1
        for (int dim = 0; dim < faces.length / 2; dim++) {

            FaceVector3D faceVec1 = new FaceVector3D(0, 0, 0);
            faceVec1.setVal(dim, 1);
            faceVecMap.put(faces[2 * dim], faceVec1);
            FaceVector3D faceVec2 = new FaceVector3D(0, 0, 0);
            faceVec2.setVal(dim, -1);
            faceVecMap.put(faces[2 * dim + 1], faceVec2);
        }
    }
    public static char[][] flipVertical(char[][] arr){
        if(arr.length!=3){
            throw new RuntimeException("Must have 3x3 array");
        }
        char[] buffer = arr[0];
        arr[0] = arr[2];
        arr[2] = buffer;
        return arr;
    }
    public static char[][] flipHorizontal(char[][] arr){
        if(arr.length!=3){
            throw new RuntimeException("Must have 3x3 array");
        }
        for(int i = 0; i <3; i++){
            char buffer = arr[i][0];
            arr[i][0] = arr[i][2];
            arr[i][2] = buffer;
        }
        return arr;
    }


    //prints 2D matrix of each side in the orientation shown below
    //   F
    // L D R
    //   B
    //   U
    public void printCube() {
        char[][] spaces = new char[3][3];
        for (char[] arr : spaces) {
            Arrays.fill(arr, ' ');
        }

        char[][] f_mat = getStickersOnFace(faceVecMap.get('F'));
        char[][] d_mat = getStickersOnFace(faceVecMap.get('D'));
        char[][] l_mat = getStickersOnFace(faceVecMap.get('L'));
        char[][] r_mat = getStickersOnFace(faceVecMap.get('R'));
        char[][] b_mat = getStickersOnFace(faceVecMap.get('B'));
        char[][] u_mat = getStickersOnFace(faceVecMap.get('U'));
        b_mat = flipVertical(b_mat);
        u_mat = flipVertical(u_mat);
        l_mat = flipHorizontal(l_mat);

        print3Squares(new char[][][]{spaces, f_mat, spaces});
        print3Squares(new char[][][]{l_mat, d_mat, r_mat});
        print3Squares(new char[][][]{spaces, b_mat, spaces});
        print3Squares(new char[][][]{spaces, u_mat, spaces});
    }

    public void print3Squares(char[][][] arr) {
        for (int y = 2; y >= 0; y--) { //y axis of arr[j]
            for (int i = 0; i < arr.length; i++) { //the array we're on
                for (int x = 0; x < 3; x++) { //x axis of arr[j]
                    System.out.print(arr[i][y][x] + " ");
                }
            }
            System.out.println();
        }
    }
    public void moveParse(String moves){
        StringTokenizer st = new StringTokenizer(moves);
        while(st.hasMoreTokens()){
            move(st.nextToken());
        }
    }
    public void move(String face){
        List<CubePiece> pieceList = new ArrayList<>();
        FaceVector3D faceVec = faceVecMap.get(face.charAt(0));
        for(int[] pos:faceVec.getPiecePosOnFace()){
            CubePiece currPiece = rubiksCube.getAtPos(pos);
            pieceList.add(currPiece);
            currPiece.rotateTransform(face);
        }
        for(CubePiece cubePiece:pieceList){
            int[] currPos = cubePiece.getPos().getVal();
            rubiksCube.setAtPos(currPos, cubePiece);
        }
    }


    public static void main(String[] args) {
        Cube c = new Cube();
        c.moveParse("R U R' U' R U R' U' R U R' U' R U R' U' R U R' U' R U R' U' ");
        c.printCube();

    }

}