package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;

/**
 * Created by Azathoth on 20. 1. 2015.
 */
public class SearchResult extends AbstractSearchResult {

    private String metaKeywords;
    private String metaDescription;

    public SearchResult(String url, SearchedWord word, SeekingLocation location, String metaKeywords, String metaDescription) {
        super(url, word, location);
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

    @Override
    public String getMetatagsForOutput() {
        String output = "";
        if (metaKeywords.equals("Tag keywords ve stránce chybí.")) {
            output += "Tag keywords ve stránce chybí.";
        } else {
            output += "<meta name=\"keywords\" content=\""+metaKeywords+"\" />";

        }
        if (metaDescription.equals("Tag description ve stránce chybí.")) {
            output += "Tag description ve stránce chybí.";
        } else {
            output += "<meta name=\"description\" content=\""+metaDescription+"\" />";
        }
        return output;
    }

}
