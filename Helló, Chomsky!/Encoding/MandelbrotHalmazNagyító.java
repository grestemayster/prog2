/*
 * MandelbrotHalmazNagyt.java
 *
 * DIGIT 2005, Javat tantok
 * Btfai Norbert, nbatfai@inf.unideb.hu
 *
 */
/**
 * A Mandelbrot halmazt nagyt s kirajzol osztly.
 *
 * @author Btfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.2
 */
public class MandelbrotHalmazNagyt extends MandelbrotHalmaz {
    /** A nagytand kijellt terletet bal fels sarka. */
    private int x, y;
    /** A nagytand kijellt terlet szlessge s magassga. */
    private int mx, my;
    /**
     * Ltrehoz egy a Mandelbrot halmazt a komplex sk
     * [a,b]x[c,d] tartomnya felett kiszmol s nygtani tud
     * <code>MandelbrotHalmazNagyt</code> objektumot.
     *
     * @param      a              a [a,b]x[c,d] tartomny a koordintja.
     * @param      b              a [a,b]x[c,d] tartomny b koordintja.
     * @param      c              a [a,b]x[c,d] tartomny c koordintja.
     * @param      d              a [a,b]x[c,d] tartomny d koordintja.
     * @param      szlessg      a halmazt tartalmaz tmb szlessge.
     * @param      itercisHatr a szmts pontossga.
     */
    public MandelbrotHalmazNagyt(double a, double b, double c, double d,
            int szlessg, int itercisHatr) {
        // Az s osztly konstruktornak hvsa
        super(a, b, c, d, szlessg, itercisHatr);
        setTitle("A Mandelbrot halmaz nagytsai");
        // Egr kattint esemnyek feldolgozsa:
        addMouseListener(new java.awt.event.MouseAdapter() {
            // Egr kattintssal jelljk ki a nagytand terletet
            // bal fels sarkt vagy ugyancsak egr kattintssal
            // vizsgljuk egy adott pont iterciit:
            public void mousePressed(java.awt.event.MouseEvent m) {
                // Az egrmutat pozcija
                x = m.getX();
                y = m.getY();
                // Az 1. egr gombbal a nagytand terlet kijellst
                // vgezzk:
                if(m.getButton() == java.awt.event.MouseEvent.BUTTON1 ) {
                    // A nagytand kijellt terletet bal fels sarka: (x,y)
                    // s szlessge (majd a vonszols nveli)
                    mx = 0;
                    my = 0;
                    repaint();
                } else {
                    // Nem az 1. egr gombbal az egrmutat mutatta c
                    // komplex szmbl indtott itercikat vizsglhatjuk
                    MandelbrotItercik itercik =
                            new MandelbrotItercik(
                            MandelbrotHalmazNagyt.this, 50);
                    new Thread(itercik).start();
                }
            }
            // Vonszolva kijellnk egy terletet...
            // Ha felengedjk, akkor a kijellt terlet
            // jraszmtsa indul:
            public void mouseReleased(java.awt.event.MouseEvent m) {
                if(m.getButton() == java.awt.event.MouseEvent.BUTTON1 ) {
                    double dx = (MandelbrotHalmazNagyt.this.b
                            - MandelbrotHalmazNagyt.this.a)
                            /MandelbrotHalmazNagyt.this.szlessg;
                    double dy = (MandelbrotHalmazNagyt.this.d
                            - MandelbrotHalmazNagyt.this.c)
                            /MandelbrotHalmazNagyt.this.magassg;
                    // Az j Mandelbrot nagyt objektum elksztse:
                    new MandelbrotHalmazNagyt(
                            MandelbrotHalmazNagyt.this.a+x*dx,
                            MandelbrotHalmazNagyt.this.a+x*dx+mx*dx,
                            MandelbrotHalmazNagyt.this.d-y*dy-my*dy,
                            MandelbrotHalmazNagyt.this.d-y*dy,
                            600,
                            MandelbrotHalmazNagyt.this.itercisHatr);
                }
            }
        });
        // Egr mozgs esemnyek feldolgozsa:
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            // Vonszolssal jelljk ki a ngyzetet:
            public void mouseDragged(java.awt.event.MouseEvent m) {
                // A nagytand kijellt terlet szlessge s magassga:
                mx = m.getX() - x;
                my = m.getY() - y;
                repaint();
            }
        });
    }
    /**
     * Pillanatfelvtelek ksztse.
     */
    public void pillanatfelvtel() {
        // Az elmentend kp elksztse:
        java.awt.image.BufferedImage mentKp =
                new java.awt.image.BufferedImage(szlessg, magassg,
                java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics g = mentKp.getGraphics();
        g.drawImage(kp, 0, 0, this);
        g.setColor(java.awt.Color.BLACK);
        g.drawString("a=" + a, 10, 15);
        g.drawString("b=" + b, 10, 30);
        g.drawString("c=" + c, 10, 45);
        g.drawString("d=" + d, 10, 60);
        g.drawString("n=" + itercisHatr, 10, 75);
        if(szmtsFut) {
            g.setColor(java.awt.Color.RED);
            g.drawLine(0, sor, getWidth(), sor);
        }
        g.setColor(java.awt.Color.GREEN);
        g.drawRect(x, y, mx, my);
        g.dispose();
        // A pillanatfelvtel kpfjl nevnek kpzse:
        StringBuffer sb = new StringBuffer();
        sb = sb.delete(0, sb.length());
        sb.append("MandelbrotHalmazNagyitas_");
        sb.append(++pillanatfelvtelSzmll);
        sb.append("_");
        // A fjl nevbe belevesszk, hogy melyik tartomnyban
        // talltuk a halmazt:
        sb.append(a);
        sb.append("_");
        sb.append(b);
        sb.append("_");
        sb.append(c);
        sb.append("_");
        sb.append(d);
        sb.append(".png");
        // png formtum kpet mentnk
        try {
            javax.imageio.ImageIO.write(mentKp, "png",
                    new java.io.File(sb.toString()));
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * A nagytand kijellt terletet jelz ngyzet kirajzolsa.
     */
    public void paint(java.awt.Graphics g) {
        // A Mandelbrot halmaz kirajzolsa
        g.drawImage(kp, 0, 0, this);
        // Ha ppen fut a szmts, akkor egy vrs
        // vonallal jelljk, hogy melyik sorban tart:
        if(szmtsFut) {
            g.setColor(java.awt.Color.RED);
            g.drawLine(0, sor, getWidth(), sor);
        }
        // A jelz ngyzet kirajzolsa:
        g.setColor(java.awt.Color.GREEN);
        g.drawRect(x, y, mx, my);
    }
    /**
     * Hol ll az egrmutat?
     * @return int a kijellt pont oszlop pozcija.
     */    
    public int getX() {
        return x;
    }
    /**
     * Hol ll az egrmutat?
     * @return int a kijellt pont sor pozcija.
     */    
    public int getY() {
        return y;
    }
    /**
     * Pldnyost egy Mandelbrot halmazt nagyt obektumot.
     */
    public static void main(String[] args) {
        // A kiindul halmazt a komplex sk [-2.0, .7]x[-1.35, 1.35]
        // tartomnyban keressk egy 600x600-as hlval s az
        // aktulis nagytsi pontossggal:
        new MandelbrotHalmazNagyt(-2.0, .7, -1.35, 1.35, 600, 255);
    }
}                    
