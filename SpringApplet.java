package lab4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Timer;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener{
    private SimEngine simE;
    private SimTask simT;
    private Timer timerObject;

    //Deklaracja pól odpowiadających za odpowiednie skalę na rysunku
    private int width = 600;
    private int height = 600;
    private int scale=5;
    private int springYScale = 6;
    private int r=3;

    //Deklaracja pól myszy
    private boolean drag;
    private int cursorPositionX;
    private int cursorPositionY;

    //Deklaracja pól tekstowych oraz przycisku
    private TextField masaField, kField, cField, lField, gField;
    private Button przycisk;


    public void init() {
        //Zdefiniowanie wymiarów okna
        setSize(width, height);
        //Zdefiniowanie obiektów
        simE = new SimEngine(3,4.5,0.19,40,35,40,5,10);
        simT = new SimTask(simE, this, 30);
        //Utworzenie obiektu timera i ustawienie odświeżania na 30ms
        timerObject = new Timer();
        timerObject.scheduleAtFixedRate(simT, 0, 30);

        //Obsluga myszy
        drag = false;
        addMouseListener(this);
        addMouseMotionListener(this);

        //Definicja pól tekstowych oraz przycisku
        masaField = new TextField();
        kField = new TextField();
        cField = new TextField();
        lField = new TextField();
        gField = new TextField();
        przycisk = new Button("Restart");

        //Dodanie pól tekstowych oraz przycisku do appletu
        add(masaField);
        add(kField);
        add(cField);
        add(lField);
        add(gField);
        add(przycisk);
        przycisk.addActionListener(this);
    }


    public void paint(Graphics g) {
        //Wyczyszczenie appletu
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        //Rysowanie ukladu wspolrzednych
        g.setColor(Color.LIGHT_GRAY);
        for(int i=0;i<width/20;i++)
        {
            g.drawLine(0,20*i,width,20*i);
            g.drawLine(20*i,0,20*i,height);
        }


        //Rysowanie utwierdzenia
        g.setColor(Color.BLACK);
        g.drawLine(width/2-100,0,width/2-100,scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2-100,scale*(int)simE.getyZawieszenia(),width/2+100,scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2+100,0,width/2+100,scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2-80,0,width/2-100,(int)(scale*simE.getyZawieszenia()/2.8));
        g.drawLine(width/2-60,0,width/2-100,(int)(scale*simE.getyZawieszenia()/1.5));
        g.drawLine(width/2-40,0,width/2-100,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2-20,0,width/2-80,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2,0,width/2-60,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2+20,0,width/2-40,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2+40,0,width/2-20,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2+60,0,width/2,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2+80,0,width/2+20,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2+100,0,width/2+40,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2+100,(int)(scale*simE.getyZawieszenia()/2.8),width/2+60,(int)(scale*simE.getyZawieszenia()));
        g.drawLine(width/2+100,(int)(scale*simE.getyZawieszenia()/1.5),width/2+80,(int)(scale*simE.getyZawieszenia()));

        //Rysowanie sprężyny oraz masy
        g.setColor(Color.BLACK);
        g.drawLine(width/2,scale*(int)simE.getyZawieszenia(),width/2+scale*20,scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2+scale*20,scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale +scale*(int)simE.getyZawieszenia(),width/2-scale*20,2*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2-scale*20,2*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia(),width/2+scale*20,3*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2+scale*20,3*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia(),width/2-scale*20,4*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2-scale*20,4*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia(),width/2+scale*20,5*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia());
        g.drawLine(width/2+scale*20,5*scale*((int)simE.getyMasy()-(int)simE.getyZawieszenia()+(int)simE.getL())/springYScale+scale*(int)simE.getyZawieszenia(),width/2,scale*((int)simE.getyMasy()+(int)simE.getL()));
        g.fillOval( width/2-scale*r,scale*((int)simE.getyMasy()+(int)simE.getL())-5,2*scale*r, 2*scale*r);

        //Rysowanie sil, predkosci, przyspieszen
        g.setColor(Color.RED);
        g.drawLine(width/16,height/2,width/16, height/2+2*(int)simE.getSila().y);
        g.drawLine(width/16+1,height/2,width/16+1, height/2+2*(int)simE.getSila().y);
        g.drawString("Sila",width/16-22,height/2);
        g.setColor(Color.BLUE);
        g.drawLine(width/6,height/2,width/6, height/2+2*(int)simE.getPredk().y);
        g.drawLine(width/6+1,height/2,width/6+1, height/2+2*(int)simE.getPredk().y);
        g.drawString("Predkosc",width/6-55,height/2);
        g.setColor(Color.MAGENTA);
        g.drawLine(width/3,height/2,width/3, height/2+2*(int)simE.getPrzysp().y);
        g.drawLine(width/3+1,height/2,width/3+1, height/2+2*(int)simE.getPrzysp().y);
        g.drawString("Przyspieszenie",width/3-90,height/2);

        //Ustawienie pol tekstowych i przycisku
        g.setColor(Color.BLACK);
        g.drawString("Masa:",2,25);
        masaField.setBounds(60,10,40,20);
        g.drawString("Wsp. spr:",2,45);
        kField.setBounds(60,30,40,20);
        g.drawString("Wsp. tł.:",2,65);
        cField.setBounds(60,50,40,20);
        g.drawString("Długość:",2,85);
        lField.setBounds(60,70,40,20);
        g.drawString("Przysp. gr.:",2,105);
        gField.setBounds(60,90,40,20);
        przycisk.setBounds(40,115,60,20);
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        //Jeśli przycisk jest trzymany to aktualizujemy pozycję masy
        if(drag)
        {
            cursorPositionX = arg0.getX();
            cursorPositionY = arg0.getY();
            simE.setyMasy(cursorPositionY/scale - simE.getL());
            repaint();
            arg0.consume();
        }
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //Odczytanie współrzędnych kursora przy kliknięciu
        cursorPositionX = e.getX();
        cursorPositionY = e.getY();
        //Jeśli kursor znajduje się na masie to zatrzymujemy symulację
        if(Math.pow(cursorPositionY-scale*((int)simE.getyMasy()+(int)simE.getL()), 2) + Math.pow(cursorPositionX - width/2, 2) <= Math.pow(2*scale*r,2))
        {
            timerObject.cancel();
            drag = true;
            simE.reset();
            e.consume();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //Po puszczeniu myszki ponownie definiuję obiekt klasy SimTask, Timer oraz ponownie wywołuję symulację
        if(drag)
        {
            simT = new SimTask(simE, this, 30);
            timerObject = new Timer();
            timerObject.scheduleAtFixedRate(simT, 0, 30);
            drag = false;
            e.consume();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == przycisk)
        {   //Zatrzymanie symulacji, ponowne zdefiniowanie obiektów klasy SimEngine, SimTask, Timer oraz ponowne wywołanie symulacji
            timerObject.cancel();
            simE = new SimEngine(Double.parseDouble(masaField.getText()),Double.parseDouble(kField.getText()),Double.parseDouble(cField.getText()),Double.parseDouble(lField.getText()),35,40,5,Double.parseDouble(gField.getText()));
            simT = new SimTask(simE, this, 30);
            timerObject = new Timer();
            timerObject.scheduleAtFixedRate(simT, 0, 30);
        }
    }

    public static void main(String[] args) { }

}