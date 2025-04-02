namespace lab9.Domain;

public class NumarComplex
{
    public double re { get; set; }
    public double im { get; set; }

    public NumarComplex(double re, double im)
    {
        this.re = re;
        this.im = im;
    }

    public static NumarComplex adunare(NumarComplex numar1, NumarComplex numar2)
    {
        return new NumarComplex(numar1.re + numar2.re, numar1.im + numar2.im);
    }

    public static NumarComplex scadere(NumarComplex numar1, NumarComplex numar2)
    {
        return new NumarComplex(numar1.re - numar2.re, numar1.im - numar2.im);
    }
    
    //(a + ib) (c + id) = (ac - bd) + i(ad + bc).
    public static NumarComplex inmultire(NumarComplex nr1, NumarComplex nr2){
        double a = nr1.re;
        double b = nr1.im;
        double c = nr2.re;
        double d = nr2.im;

        return new NumarComplex(a*c-b*d, a*d+b*c);
    }
    
    public static NumarComplex impartire(NumarComplex nr1, NumarComplex nr2)
    {
        double a = nr1.re;
        double b = nr1.im;
        double c = nr2.re;
        double d = nr2.im;

        return new NumarComplex((a*c+b*d)/(c*c+d*d), (b*c-a*d)/(c*c+d*d));
    }

    public override string ToString()
    {
        string rezultat = re.ToString();
        if (im > 0)
            rezultat += "+";
        if (im != 0)
            rezultat += im.ToString() + "*i";
        return rezultat;
    }
}