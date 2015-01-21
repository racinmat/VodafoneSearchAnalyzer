package VodafoneSearchAnalyzer;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchResult extends AbstractSearchResult {

    private String metaKeywords;
    private String metaDescription;

    public SearchResult(String url, String word, Category category, String metaKeywords, String metaDescription) {
        super(url, word, category);
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
        return "VodafoneSearchAnalyzer.SearchResult{" +
                "metaKeywords='" + metaKeywords + '\'' +
                ", metaDescription='" + metaDescription + '\'' +
                "} " + super.toString();
    }

}
