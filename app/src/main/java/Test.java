public class Test {
    static void printGrid(int r, int c) {
        int lim = (c-1)*r;
        for (int i = 1; i <= r; i++) {
            for (int j = i; j <=(lim+i); j+=r) {
                System.out.print(j+(j==lim+i ? "" : " "));
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        printGrid(4, 6);
    }
}
