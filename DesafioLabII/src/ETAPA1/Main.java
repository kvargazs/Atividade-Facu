package ETAPA1;

public class Main {
    public static void main(String[] args) {
        Labirinto l = new Labirinto();
        l.criaLabirinto("src/ETAPA1/labirinto.txt"); //caminho do arquivo do labirinto
        l.imprimirLabirinto();

        System.out.println("Existe saída? " + l.percorreLabirinto());
    }
}
