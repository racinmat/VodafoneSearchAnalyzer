/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class VodafoneOfferSeeker extends VodafoneAbstractSeeker {

    public VodafoneOfferSeeker(int results) {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=16&sc=", "nabídka", results);
    }

    public VodafoneOfferSeeker() {
        super("https://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=16&sc=", "nabídka", 0);
    }

}
