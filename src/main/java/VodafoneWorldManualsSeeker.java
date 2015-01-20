/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class VodafoneWorldManualsSeeker extends VodafoneAbstractSeeker {

    public VodafoneWorldManualsSeeker(int results) {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=3&sc=", "nastavení", results);
    }

    public VodafoneWorldManualsSeeker() {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=3&sc=", "nastavení", 0);
    }

}
