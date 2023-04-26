package barbearia;
import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Barbearia implements Runnable{
    private String threadName;
    
    public Barbearia() {}
    
    private final Queue<Integer> espera = new LinkedList<>(); // fila de espera
    private final Queue<Integer> sofa = new LinkedList<>(); // fila de espera
    private int proximo = 0; // próximo cliente a ser atendido

    public static void pagamento(){}

    public void run() {

        Barbearia thread1 = new Barbearia();
        Barbearia thread2 = new Barbearia();
        Barbearia thread3 = new Barbearia();

        Thread barbeiro1 = new Thread(thread1);
        Thread barbeiro2 = new Thread(thread2);
        Thread barbeiro3 = new Thread(thread3);

        barbeiro1.start();         
        barbeiro2.start();       
        barbeiro3.start();

        while (true) {
            try {
                // Cliente chegou na barbearia
                
                if (espera.size() >= 13) {
                    System.out.println("A barbearia está cheia! O cliente " + proximo + " foi embora.");
                }
                espera.add(proximo);
                System.out.println("O cliente " + proximo + " chegou na barbearia e está aguardando atendimento.");
                
                // Cliente senta no sofá
                sofa.add(proximo);
                System.out.println("O cliente " + proximo + " Sentou no sofa.");
                proximo++;
                
                // Barbeiro atende cliente
                for (int i = 0; i < 3; i++) {
                        int cliente = espera.remove();
                        System.out.println("O barbeiro " + i + " está cortando o cabelo do cliente " + cliente + ".");
                        TimeUnit.SECONDS.sleep(1); // Tempo que o barbeiro leva para cortar o cabelo
                    
                        // Cliente paga    
                        System.out.println("O cliente " + cliente + " está realizando o pagamento.");
                        TimeUnit.SECONDS.sleep(3); // Tempo que o cliente leva para pagar
                        
                        break;
                    }
                
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Barbearia p = new Barbearia();
        p.run();

        while(true){
            Barbearia thread = new Barbearia();
            Thread t = new Thread(thread);
            t.start();
        }

      //na thread cliente só precisa se preoucpar com se a barbearia ta cheia e se tem lugar no sofa
      //se fila is not null barbeiro puxa da fila 
      // uma lista pros de pé e uma lista pros de pé e uma lista pro sofa 
      //a parte do pagamento pode ser qualquer variavel tem que ta no sychronized
    }
    
}
