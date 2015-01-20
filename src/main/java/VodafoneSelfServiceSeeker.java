/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class VodafoneSelfServiceSeeker extends VodafoneAbstractSeeker {

    public VodafoneSelfServiceSeeker(int results) {
        super("http://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=17&sc=", "samoobsluha", results);
    }

    public VodafoneSelfServiceSeeker() {
        super("http://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=17&sc=", "samoobsluha", 0);
    }

}
