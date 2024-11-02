package Domain;

public class NumarComplex {
    private final double re;
    private final double im;

    public NumarComplex(double real, double imaginar){
        re = real;
        im = imaginar;
    }

    public double getIm() {
        return im;
    }

    public double getRe() {
        return re;
    }

    public static NumarComplex adunare(NumarComplex nr1, NumarComplex nr2){
        return new NumarComplex(nr1.getRe() + nr2.getRe(), nr1.getIm() + nr2.getIm());
    }

    public static NumarComplex scadere(NumarComplex nr1, NumarComplex nr2){
        return new NumarComplex(nr1.getRe() - nr2.getRe(), nr1.getIm() - nr2.getIm());
    }

    //(a + ib) (c + id) = (ac - bd) + i(ad + bc).
    public static NumarComplex inmultire(NumarComplex nr1, NumarComplex nr2){
        double a = nr1.getRe();
        double b = nr1.getIm();
        double c = nr2.getRe();
        double d = nr2.getIm();

        return new NumarComplex(a*c-b*d, a*d+b*c);
    }

    public static NumarComplex impartire(NumarComplex nr1, NumarComplex nr2){
        double a = nr1.getRe();
        double b = nr1.getIm();
        double c = nr2.getRe();
        double d = nr2.getIm();

        return new NumarComplex((a*c+b*d)/(c*c+d*d), (b*c-a*d)/(c*c+d*d));
    }

    @Override
    public String toString(){
        String rezultat = String.valueOf(re);
        if (im > 0)
            rezultat += "+";
        if (im != 0)
            rezultat += String.valueOf(im) + "*i";
        return rezultat;
    }
}
