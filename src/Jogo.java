import java.util.Scanner;

public class Jogo   {
    Scanner e = new Scanner(System.in);
    private int posicao1,posicao2;
    private char[][] Tabuleiro = new char[3][3];
    private int vez = 0;
    Boolean ganhou = false;
    int count =0 ;
    public void Jogar () {
        while(!ganhou){
            if(vez == 0) {
                MostrarTabuleiro();
                System.out.println("Turno do jogador 1");
                System.out.println("Escolha a posição que deseja jogar, primeiro a linha depois a coluna");
                posicao1 = e.nextInt();
                while (!ValidarP1()) {
                    System.out.println("JOGADA INVALIDA");
                    posicao1 = e.nextInt();
                }
                posicao2 = e.nextInt();
                while (!ValidarP2()) {
                    System.out.println("JOGADA INVALIDA");
                    posicao2 = e.nextInt();
                }
                while (!ValidarTab()) {
                    System.out.println("JOGADA INVALIDA,ESCREVA AS POSIÇÕES NOVAMENTE");
                    posicao1 = e.nextInt();
                    while (!ValidarP1()) {
                        System.out.println("POSIÇÃO 1 INVALIDA");
                        posicao1 = e.nextInt();
                    }
                    posicao2 = e.nextInt();
                    while (!ValidarP2()) {
                        System.out.println("POSIÇÃO 2 INVALIDA");
                        posicao2 = e.nextInt();
                    }
                }
                Tabuleiro[posicao1][posicao2] = 'X';
                count++;
                vez = 1;
            }
            MostrarTabuleiro();
            if(VerificarVitoria()){
                ganhou = true;
                System.out.println("J1 GANHOU");
                break;
            }
            if(VerificarVelha()){
                System.out.println("Deu velha");
                break;
            }

            if(vez == 1){
                System.out.println("Turno do jogador 2");
                System.out.println("Escolha a posição que deseja jogar, primeiro a linha depois a coluna");
                posicao1 = e.nextInt();
                while (!ValidarP1()) {
                    System.out.println("JOGADA INVALIDA");
                    posicao1 = e.nextInt();
                }
                posicao2 = e.nextInt();
                while (!ValidarP2()) {
                    System.out.println("JOGADA INVALIDA");
                    posicao2 = e.nextInt();
                }
                while (!ValidarTab()) {
                    System.out.println("JOGADA INVALIDA");
                    posicao1 = e.nextInt();
                    while (!ValidarP1()) {
                        System.out.println("POSIÇÃO 1 INVALIDA");
                        posicao1 = e.nextInt();
                    }
                    posicao2 = e.nextInt();
                    while (!ValidarP2()) {
                        System.out.println("POSIÇÃO 2 INVALIDA");
                        posicao2 = e.nextInt();
                    }
                }
                Tabuleiro[posicao1][posicao2] = 'O';
                count++;
                vez = 0;

            }
            MostrarTabuleiro();
            if(VerificarVitoria()){
                ganhou = true;
                System.out.println("J2 GANHOU");
                break;
            }
            if(VerificarVelha()){
                System.out.println("Deu velha");
                break;
            }

        }
    }
    private boolean ValidarP1(){
        if(posicao1 >= 0 && posicao1 < 3){
            return   true;
        }else{
            return  false;
        }
    }
    private boolean ValidarP2(){
        if(posicao2 >= 0 && posicao2 < 3){
            return   true;
        }else{
            return  false;
        }
    }
    private boolean ValidarTab(){
        if(Tabuleiro[posicao1][posicao2] == 0){
            return  true;
        }else{
        return   false;
        }
    }
    public boolean VerificarVitoria() {
        for (int i = 0; i < 3; i++) {
            if (Tabuleiro[i][0] != 0 &&
                    Tabuleiro[i][0] == Tabuleiro[i][1] &&
                    Tabuleiro[i][1] == Tabuleiro[i][2]) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (Tabuleiro[0][j] != 0 &&
                    Tabuleiro[0][j] == Tabuleiro[1][j] &&
                    Tabuleiro[1][j] == Tabuleiro[2][j]) {
                return true;
            }
        }
        if (Tabuleiro[0][0] != 0 &&
                Tabuleiro[0][0] == Tabuleiro[1][1] &&
                Tabuleiro[1][1] == Tabuleiro[2][2]) {
            return true;
        }
        if (Tabuleiro[0][2] != 0 &&
                Tabuleiro[0][2] == Tabuleiro[1][1] &&
                Tabuleiro[1][1] == Tabuleiro[2][0]) {
            return true;
        }

        return false;
    }
    public void MostrarTabuleiro() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (Tabuleiro[i][j] == 0) {
                    System.out.printf(" %d,%d ", i, j);
                } else {
                    System.out.printf("  %c  ", Tabuleiro[i][j]);
                }

                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("-----+-----+-----");
            }
        }
        System.out.println();
    }
    public boolean VerificarVelha(){
        if(count == 9){
            return  true;
        }else{
            return false;
        }
    }
    public int getVez(){
        return vez;
    }
    public char getSimboloAtual(){
        return vez == 0 ? 'X' : 'O' ;
    }
    public void proximoTurno(){
        vez = (vez ==0) ? 1 : 0;
    }
    public void fazerJogada(int linha,int coluna){
        Tabuleiro[linha][coluna] = getSimboloAtual();
        count++;
    }
    public void Revanche(){
        vez = 0;
        ganhou = false;
        count = 0;
        for(int i = 0; i < 3;i++){
            for(int j =0;j < 3;j++){
                if(Tabuleiro[i][j] != 0){
                    Tabuleiro[i][j] =0;
                }
            }
        }
    }

}

