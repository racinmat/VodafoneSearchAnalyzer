/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class VodafoneCareCenterSeeker extends VodafoneAbstractSeeker {

    public VodafoneCareCenterSeeker(int results) {
        super("http://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=2&sc=", "centrum péče", results);
    }

    public VodafoneCareCenterSeeker() {
        super("http://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=2&sc=", "centrum péče", 0);
    }

}
