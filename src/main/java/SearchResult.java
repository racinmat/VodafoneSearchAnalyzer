/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchResult extends AbstractSearchResult {

    private String metaKeywords;
    private String metaDescription;

    public SearchResult(String url, String metaKeywords, String metaDescription) {
        super(url);
        this.metaKeywords = metaKeywords;
        this.metaDescription = metaDescription;
    }

    public String getUrl() {
        return url;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "url='" + url + '\'' +
                ", metaKeywords='" + metaKeywords + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                '}';
    }
}
