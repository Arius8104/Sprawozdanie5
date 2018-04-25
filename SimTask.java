package lab4;
        import java.util.TimerTask;

public class SimTask extends TimerTask {
    private SimEngine simE;
    private SpringApplet springA;
    private int delay;

    //Konstruktor z parametrami
    public SimTask(SimEngine simE, SpringApplet springA, int delay) {
        this.simE = simE;
        this.springA = springA;
        this.delay = delay;
    }

    //Metoda obliczająca fizykę i odświeżająca applet
    public void run() {
        simE.przebiegSymulacji(delay);
        springA.repaint();
    }
}