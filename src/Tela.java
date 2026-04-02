import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;


public class Tela {
    private Jogo jogo;
    JButton[][] botoes = new JButton[3][3];
    public Tela(Jogo jogo){
        this.jogo = jogo;
        criarUI();
    }

    private void criarUI(){
        JFrame Janela = new JFrame("Jogo da Velha");

        Janela.setSize(500,500);
        Janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Painel = new JPanel();
        Painel.setLayout(new GridLayout(3,3));
        JPanel Revanche = new JPanel();

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    JButton botao = new JButton("");
                    botao.setFont(new Font("Serif", Font.BOLD, 60));
                    botao.setBackground(Color.white);


                    int linha = i;
                    int coluna = j;

                    botao.addActionListener(e -> {

                            botao.setText(String.valueOf(jogo.getSimboloAtual()));
                            if(jogo.getSimboloAtual() == 'X'){
                                botao.setBackground(Color.black);
                            }else{
                                botao.setBackground(Color.red);
                            }
                            botao.setEnabled(false);

                            jogo.fazerJogada(linha,coluna);
                            if(jogo.VerificarVitoria()){
                                JOptionPane.showMessageDialog(null,"Jogador "+jogo.getSimboloAtual()+" ganhou");
                                this.bloquearTabuleiro();
                                int opcao = JOptionPane.showConfirmDialog(
                                        null,
                                        "Deseja jogar novamente?",
                                        "Fim de jogo",
                                        JOptionPane.YES_NO_OPTION
                                );
                                if(opcao == JOptionPane.YES_OPTION){
                                    jogo.Revanche();
                                    resetarTela();
                                }else{
                                    bloquearTabuleiro();
                                }


                                return;
                            }
                            if(jogo.VerificarVelha()){
                                JOptionPane.showMessageDialog(null,"DEU VELHA");
                                int opcao = JOptionPane.showConfirmDialog(
                                        null,
                                        "Deseja jogar novamente?",
                                        "Fim de jogo",
                                        JOptionPane.YES_NO_OPTION
                                );
                                if(opcao == JOptionPane.YES_OPTION){
                                    jogo.Revanche();
                                    resetarTela();
                                }else{
                                    bloquearTabuleiro();
                                }

                                return;
                            }

                            jogo.proximoTurno();
                    });
                    this.botoes[i][j] = botao;
                    Painel.add(botao);

                }
            }
        Janela.add(Painel);
        Janela.setVisible(true);


    }

    private void bloquearTabuleiro() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setEnabled(false);
            }
        }
    }
    private void resetarTela() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
                botoes[i][j].setEnabled(true);
                botoes[i][j].setBackground(Color.white);
            }
        }
    }

}
