public class Main {
    public static void main(String[] args) {
        Labirinto l = new Labirinto();
        l.criaLabirinto("src/labirinto.txt");
        l.imprimirLabirinto();

        System.out.println("Existe saída? " + l.percorreLabirinto());
    }
}
