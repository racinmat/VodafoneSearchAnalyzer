import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class VodafoneSelfServiceSeeker extends VodafoneAbstractSeeker {

    public VodafoneSelfServiceSeeker() {
        super("http://www.vodafone.cz/vyhledavani/?q=", "&l=cs&c=17&sc=");
    }



}
