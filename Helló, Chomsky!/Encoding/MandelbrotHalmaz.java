/*
 * MandelbrotHalmaz.java
 *
 * DIGIT 2005, Javat tantok
 * Btfai Norbert, nbatfai@inf.unideb.hu
 *
 */
/**
 * A Mandelbrot halmazt kiszmol s kirajzol osztly.
 *
 * @author Btfai Norbert, nbatfai@inf.unideb.hu
 * @version 0.0.2
 */
public class MandelbrotHalmaz extends java.awt.Frame implements Runnable {
    /** A komplex sk vizsglt tartomnya [a,b]x[c,d]. */
    protected double a, b, c, d;
    /** A komplex sk vizsglt tartomnyra fesztett
     * hl szlessge s magassga. */
    protected int szlessg, magassg;
    /** A komplex sk vizsglt tartomnyra fesztett hlnak megfelel kp.*/
    protected java.awt.image.BufferedImage kp;
    /** Max. hny lpsig vizsgljuk a z_{n+1} = z_n * z_n + c itercit?
     * (tk. most a nagytsi pontossg) */
    protected int itercisHatr = 255;
    /** Jelzi, hogy ppen megy-e a szamts? */
    protected boolean szmtsFut = false;
    /** Jelzi az ablakban, hogy ppen melyik sort szmoljuk. */
    protected int sor = 0;
    /** A pillanatfelvtelek szmozshoz. */
    protected static int pillanatfelvtelSzmll = 0;
    /**
     * Ltrehoz egy a Mandelbrot halmazt a komplex sk
     * [a,b]x[c,d] tartomnya felett kiszmol
     * <code>MandelbrotHalmaz</code> objektumot.
     *
     * @param      a              a [a,b]x[c,d] tartomny a koordintja.
     * @param      b              a [a,b]x[c,d] tartomny b koordintja.
     * @param      c              a [a,b]x[c,d] tartomny c koordintja.
     * @param      d              a [a,b]x[c,d] tartomny d koordintja.
     * @param      szlessg      a halmazt tartalmaz tmb szlessge.
     * @param      itercisHatr a szmts pontossga.
     */
    public MandelbrotHalmaz(double a, double b, double c, double d,
            int szlessg, int itercisHatr) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.szlessg = szlessg;
        this.itercisHatr = itercisHatr;
        // a magassg az (b-a) / (d-c) = szlessg / magassg
        // arnybl kiszmolva az albbi lesz:
        this.magassg = (int)(szlessg * ((d-c)/(b-a)));
        // a kp, amire rrajzoljuk majd a halmazt
        kp = new java.awt.image.BufferedImage(szlessg, magassg,
                java.awt.image.BufferedImage.TYPE_INT_RGB);
        // Az ablak bezrsakor kilpnk a programbl.
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        // A billentyzetrl rkez esemnyek feldolgozsa
        addKeyListener(new java.awt.event.KeyAdapter() {
            // Az 's', 'n' s 'm' gombok lenyomst figyeljk
            public void keyPressed(java.awt.event.KeyEvent e) {
                if(e.getKeyCode() == java.awt.event.KeyEvent.VK_S)
                    pillanatfelvtel();
                // Az 'n' gomb benyomsval pontosabb szmtst vgznk.
                else if(e.getKeyCode() == java.awt.event.KeyEvent.VK_N) {
                    if(szmtsFut == false) {
                        MandelbrotHalmaz.this.itercisHatr += 256;
                        // A szmts jra indul:
                        szmtsFut = true;
                        new Thread(MandelbrotHalmaz.this).start();
                    }
                    // Az 'm' gomb benyomsval pontosabb szmtst vgznk,
                    // de kzben sokkal magasabbra vesszk az itercis
                    // hatrt, mint az 'n' hasznlata esetn
                } else if(e.getKeyCode() == java.awt.event.KeyEvent.VK_M) {
                    if(szmtsFut == false) {
                        MandelbrotHalmaz.this.itercisHatr += 10*256;
                        // A szmts jra indul:
                        szmtsFut = true;
                        new Thread(MandelbrotHalmaz.this).start();
                    }
                }
            }
        });
        // Ablak tulajdonsgai
        setTitle("A Mandelbrot halmaz");
        setResizable(false);
        setSize(szlessg, magassg);
        setVisible(true);
        // A szmts indul:
        szmtsFut = true;
        new Thread(this).start();
    }
    /** A halmaz aktulis llapotnak kirajzolsa. */
    public void paint(java.awt.Graphics g) {
        // A Mandelbrot halmaz kirajzolsa
        g.drawImage(kp, 0, 0, this);
        // Ha ppen fut a szmts, akkor egy vrs
        // vonallal jelljk, hogy melyik sorban tart:
        if(szmtsFut) {
            g.setColor(java.awt.Color.RED);
            g.drawLine(0, sor, getWidth(), sor);
        }
    }
    // Ne villogjon a fellet (mert a "gyri" update()
    // lemeszeln a vszon fellett).
    public void update(java.awt.Graphics g) {
        paint(g);
    }
    /** Pillanatfelvtelek ksztse. */
    public void pillanatfelvtel() {
        // Az elmentend kp elksztse:
        java.awt.image.BufferedImage mentKp =
                new java.awt.image.BufferedImage(szlessg, magassg,
                java.awt.image.BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics g = mentKp.getGraphics();
        g.drawImage(kp, 0, 0, this);
        g.setColor(java.awt.Color.BLUE);
        g.drawString("a=" + a, 10, 15);
        g.drawString("b=" + b, 10, 30);
        g.drawString("c=" + c, 10, 45);
        g.drawString("d=" + d, 10, 60);
        g.drawString("n=" + itercisHatr, 10, 75);
        g.dispose();
        // A pillanatfelvtel kpfjl nevnek kpzse:
        StringBuffer sb = new StringBuffer();
        sb = sb.delete(0, sb.length());
        sb.append("MandelbrotHalmaz_");
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
     * A Mandelbrot halmaz szmtsi algoritmusa.
     * Az algoritmus rszletes ismertetst lsd pldul a
     * [BARNSLEY KNYV] (M. Barnsley: Fractals everywhere, 
     * Academic Press, Boston, 1986) hivatkozsban vagy 
     * ismeretterjeszt szinten a [CSSZR KNYV] hivatkozsban.
     */
    public void run() {
        // A [a,b]x[c,d] tartomnyon milyen sr a
        // megadott szlessg, magassg hl:
        double dx = (b-a)/szlessg;
        double dy = (d-c)/magassg;
        double reC, imC, reZ, imZ, ujreZ, ujimZ;
        int rgb;
        // Hny itercit csinltunk?
        int iterci = 0;
        // Vgigzongorzzuk a szlessg x magassg hlt:
        for(int j=0; j<magassg; ++j) {
            sor = j;
            for(int k=0; k<szlessg; ++k) {
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
                while(reZ*reZ + imZ*imZ < 4 && iterci < itercisHatr) {
                    // z_{n+1} = z_n * z_n + c
                    ujreZ = reZ*reZ - imZ*imZ + reC;
                    ujimZ = 2*reZ*imZ + imC;
                    reZ = ujreZ;
                    imZ = ujimZ;
                    
                    ++iterci;
                    
                }
                // ha a < 4 felttel nem teljeslt s a
                // iterci < itercisHatr srlsvel lpett ki, azaz
                // feltesszk a c-rl, hogy itt a z_{n+1} = z_n * z_n + c
                // sorozat konvergens, azaz iterci = itercisHatr
                // ekkor az iterci %= 256 egyenl 255, mert az esetleges
                // nagytasok sorn az iterci = valahny * 256 + 255
                iterci %= 256;
                // gy a halmaz elemeire 255-255 rtket hasznljuk,
                // azaz (Red=0,Green=0,Blue=0) fekete sznnel:
                rgb = (255-iterci)|
                        ((255-iterci) << 8) |
                        ((255-iterci) << 16);
                // rajzoljuk a kpre az ppen vizsglt pontot:
                kp.setRGB(k, j, rgb);
            }
            repaint();
        }
        szmtsFut = false;
    }
    /** Az aktulis Mandelbrot halmaz [a,b]x[c,d] adatai.
     * @return double a */
    public double getA() {
        return a;
    }
    /** Az aktulis Mandelbrot halmaz [a,b]x[c,d] adatai.
     * @return double b */
    public double getB() {
        return b;
    }
    /** Az aktulis Mandelbrot halmaz [a,b]x[c,d] adatai.
     * @return double c */
    public double getC() {
        return c;
    }
    /** Az aktulis Mandelbrot halmaz [a,b]x[c,d] adatai.
     * @return double d */
    public double getD() {
        return d;
    }
    /** Az aktulis Mandelbrot halmaz feletti rcs adatai.
     * @return int szlessg */    
    public int getSz() {
        return szlessg;
    }
    /** Az aktulis Mandelbrot halmaz feletti rcs adatai.
     * @return int magassg */    
    public int getM() {
        return magassg;
    }
    /** Az aktulis Mandelbrot halmazt tartalmaz kp.
     * @return BufferedImage kp */    
    public java.awt.image.BufferedImage kp() {
        return kp;
    }
    /** Pldnyost egy Mandelbrot halmazt kiszmol obektumot. */
    public static void main(String[] args) {
        // A halmazt a komplex sk [-2.0, .7]x[-1.35, 1.35] tartomnyban
        // keressk egy 400x400-as hlval:
        new MandelbrotHalmaz(-2.0, .7, -1.35, 1.35, 600, 255);
    }
}                    
