/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class VodafoneOverAllSeeker extends VodafoneAbstractSeeker {

    public VodafoneOverAllSeeker(int results) {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=&sc=", "všechny kategorie", results);
    }

    public VodafoneOverAllSeeker() {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=&sc=", "všechny kategorie", 0);
    }

}
