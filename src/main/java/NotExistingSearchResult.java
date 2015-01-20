/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class NotExistingSearchResult extends AbstractSearchResult {

    private String errorMessage;

    public NotExistingSearchResult(String url) {
        super(url);
        this.errorMessage = "404 error, stránka neexistuje.";
    }

    @Override
    public String toString() {
        return "NotExistingSearchResult{" +
                "errorMessage='" + errorMessage + '\'' +
                '}';
    }
}