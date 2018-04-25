package lab4;

public class SimEngine {
    private double masa;
    private double k;
    private double c;
    private double l;
    private double yMasy;
    private double yZawieszenia;
    private double g;
    private Vector2D sila = new Vector2D();
    private Vector2D przysp = new Vector2D();
    private Vector2D predk = new Vector2D();

    //Konstruktor z parametrami
    public SimEngine(double masa, double k, double c, double l, double yMasy, double v, double yZawieszenia, double g) {
        this.masa = masa;
        this.k = k;
        this.c = c;
        this.l = l;
        this.yMasy = yMasy;
        predk.y = v;
        this.yZawieszenia = yZawieszenia;
        this.g = g;
    }


    //Gettery
    public double getMasa() {
        return masa;
    }

    public double getK() {
        return k;
    }

    public double getC() {
        return c;
    }

    public double getL() {
        return l;
    }

    public double getyMasy() {
        return yMasy;
    }

    public double getV() {
        return predk.y;
    }

    public double getyZawieszenia() {
        return yZawieszenia;
    }

    public double getG() {
        return g;
    }

    public Vector2D getSila() {
        return sila;
    }

    public Vector2D getPrzysp() {
        return przysp;
    }

    public Vector2D getPredk() {
        return predk;
    }

    //Settery
    public void setMasa(double masa) {
        this.masa = masa;
    }

    public void setK(double k) {
        this.k = k;
    }

    public void setC(double c) {
        this.c = c;
    }

    public void setL(double l) {
        this.l = l;
    }

    public void setyMasy(double yMasy) {
        this.yMasy = yMasy;
    }

    public void setV(double v) {
        predk.y = v;
    }

    public void setyZawieszenia(double yZawieszenia) {
        this.yZawieszenia = yZawieszenia;
    }

    public void setG(double g) {
        this.g = g;
    }

    //Główna metoda symulująca fizykę wahadła
    public void przebiegSymulacji(int delay)
    {
        sila.y = 0;
        przysp.y = 0;
        sila = sila.roznicaWektorow(0,k*(yMasy-yZawieszenia));
        sila = sila.roznicaWektorow(0,c*predk.y);
        sila = sila.sumaWektorow(0,masa*g);
        przysp = sila.mnozenieWektorow(0,1/masa);
        predk = predk.sumaWektorow(0,przysp.y*delay/250);
        yMasy = yMasy + predk.y * delay/250 + przysp.y*delay*delay/125000;
    }

    //Metoda ustawiająca prędkość na 0
    public void reset(){
        predk.x = 0;
        predk.y = 0;
    }
}