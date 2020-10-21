/*
 * MandelbrotItercik.java
 *
 * DIGIT 2005, Javat tantok
 * Btfai Norbert, nbatfai@inf.unideb.hu
 *
 */
/**
 * A nagytott Mandelbrot halmazok adott pontjban kpes
 * nyomonkvetni az z_{n+1} = z_n * z_n + c itercit.
 *
 * @author Btfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.1
 */
public class MandelbrotItercik implements Runnable{
    /** Mennyi idt vrakozzunk kt iterci bemutatsa kztt? */
    private int vrakozs;
    // Kiss igaz redundnsan, s nem szpen, de knyelmesen:
    private MandelbrotHalmazNagyt nagyt;
    private int j, k;
    private double a, b, c, d;
    private  int szlessg, magassg;
    private java.awt.image.BufferedImage kp;
    /**
     * Ltrehoz egy itercikat vizsgl <code>MandelbrotItercik</code>
     * szl objektumot egy adott <code>MandelbrotHalmaznagyt</code>
     * objektumhoz.
     *
     * @param      nagyt      egy <code>MandelbrotHalmazNagyt</code> objektum
     * @param      vrakozs    vrakozsi id
     */
    public MandelbrotItercik(MandelbrotHalmazNagyt nagyt, int vrakozs) {        
        this.nagyt = nagyt;
        this.vrakozs = vrakozs;
        j = nagyt.getY();
        k = nagyt.getX();
        a = nagyt.getA();
        b = nagyt.getB();
        c = nagyt.getC();
        d = nagyt.getD();
        kp = nagyt.kp();
        szlessg  = nagyt.getSz();
        magassg = nagyt.getM();
    }
    /** Az vizsglt pontbl indul itercik bemutatsa. */
    public void run() {
        /* Az albbi kd javarszt a MandelbrotHalmaz.java szmolst 
         vgz run() mdszerbl szrmazik, hiszen ugyanazt csinljuk,
         csak most nem a hln megynk vgig, hanem a hl adott a
         pldnyostsunkkor az egrmutat mutatta csompontjban (ennek
         felel meg a c kompelx szm) szmolunk, teht a kt kls for 
         ciklus nem kell. */
        // A [a,b]x[c,d] tartomnyon milyen sr a
        // megadott szlessg, magassg hl:
        double dx = (b-a)/szlessg;
        double dy = (d-c)/magassg;
        double reC, imC, reZ, imZ, ujreZ, ujimZ;
        // Hny itercit csinltunk?
        int iterci = 0;
        // c = (reC, imC) a hl rcspontjainak
        // megfelel komplex szm
        reC = a+k*dx;
        imC = d-j*dy;
        // z_0 = 0 = (reZ, imZ)
        reZ = 0;
        imZ = 0;
        iterci = 0;
        // z_{n+1} = z_n * z_n + c itercik
        // szmtsa, amg |z_n| < 2 vagy mg
        // nem rtk el a 255 itercit, ha
        // viszont elrtk, akkor gy vesszk,
        // hogy a kiindulci c komplex szmra
        // az iterci konvergens, azaz a c a
        // Mandelbrot halmaz eleme
        while(reZ*reZ + imZ*imZ < 4 && iterci < 255) {
            // z_{n+1} = z_n * z_n + c
            ujreZ = reZ*reZ - imZ*imZ + reC;
            ujimZ = 2*reZ*imZ + imC;
         
            // az iterci (reZ, imZ) -> (ujreZ, ujimZ)
            // ezt az egyenest kell kirajzolnunk, de most
            // a komplex szmokat vissza kell transzformlnunk
            // a rcs oszlop, sor koordintjv:
            java.awt.Graphics g = kp.getGraphics();
            g.setColor(java.awt.Color.WHITE);
            g.drawLine(
                    (int)((reZ - a)/dx),
                    (int)((d - imZ)/dy),
                    (int)((ujreZ - a)/dx),
                    (int)((d - ujimZ)/dy)
                    );
            g.dispose();
            nagyt.repaint();
            
            reZ = ujreZ;
            imZ = ujimZ;
            
            ++iterci;
            // Vrakozunk, hogy kzben csodlhassuk az iterci
            // ltvnyt:
            try {
                Thread.sleep(vrakozs);
            } catch (InterruptedException e) {}
        }
    }    
}                    
