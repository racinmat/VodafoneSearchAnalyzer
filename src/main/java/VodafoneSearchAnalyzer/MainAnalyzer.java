package VodafoneSearchAnalyzer;

import VodafoneSearchAnalyzer.SearchResult.*;
import VodafoneSearchAnalyzer.SearchResult.ExcelOutput.WriteExcel;
import VodafoneSearchAnalyzer.SearchedWord.SearchWordsProvider;
import VodafoneSearchAnalyzer.SearchedWord.SearchedWord;
import VodafoneSearchAnalyzer.Seekers.PublicWebSeeker;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 Copyright (c) 2014, Matěj Račinský
 All rights reserved.

 Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
 disclaimer in the documentation and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
public class MainAnalyzer {

    public static void main(String[] args) throws Exception {
        setTrustAllCerts();
        OverAllSeeker seeker = new OverAllSeeker(new PublicWebSeeker(10));

//        List<SearchedWord> wordsToBeSearched = SearchWordsProvider.getWordsToBeSearched();
//        FileSerializer.serialize(wordsToBeSearched);
//
//        List<SearchedWord> wordsToBeSearched = FileSerializer.deserializeSearchedWords();
//        System.out.println(wordsToBeSearched.size());
//        List<SearchResultsCollection> results = seeker.searchForWords(wordsToBeSearched, true);        //it is lazy
//        FileSerializer.serialize(results);
//
//        System.out.println("serialized");
//        List<SearchResultsCollection> resultsCollection = FileSerializer.deserializeSeachredWordCollections();

//        List<LazySearchResult> resultsCount = FileSerializer.deserializeSearchResultsWithoutTags();
//        System.out.println("resultsCount size: "+resultsCollection.size());
//        List<SearchResultsCollection> loadedResults = seeker.lazyLoadResults(resultsCollection, true);
//        System.out.println("loaded resultsCount size: "+loadedResults.size());
//        FileSerializer.serialize(loadedResults);
//
        List<SearchResultsCollection> loadedResults = FileSerializer.deserializeSeachredWordCollections();
        SearchResultsPersister persister = new SearchResultsPersister();
        persister.persistSearchResults(loadedResults);
        System.out.println("Everything is done.");
//
//        Connection connection = Jsoup.connect("https://www.oskarta.cz/");
//        Document page = connection.get();
//        page.select("div[name=description]");

//        SearchedWord word = new SearchedWord("fa", 5, Category.WORLD_MANUALS, SeekingLocation.PUBLIC_WEB);
//        AbstractSearchResult result = new HttpsSearchResult("te", word, SeekingLocation.PUBLIC_WEB);
//        System.out.println(result.getClass().equals(Serialized.SEARCH_RESULTS.getObjectClass()));
//        List<AbstractSearchResult> list = new ArrayList<>();
//        list.add(result);
//        System.out.println(Serialized.createFromList(list).getObjectClass());

//        List<NotLazySearchResult> resultsCount = FileSerializer.deserializeSearchResults();
//        System.out.println(resultsCount.size());
//        int count = 0;
//        for (AbstractSearchResult result : resultsCount) {
//            if (result instanceof HttpsSearchResult) {
//                count++;
//            }
//        }
//        System.out.println(count);
//        resultsCount = seeker.tryToReadHttpsResults(resultsCount);
//        count = 0;
//        for (AbstractSearchResult result : resultsCount) {
//            if (result instanceof HttpsSearchResult) {
//                count++;
//            }
//        }
//        System.out.println(seeker.chooseSeekerByWord(word));
    }

    private static void setTrustAllCerts() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                    public void checkClientTrusted( java.security.cert.X509Certificate[] certs, String authType ) {	}
                    public void checkServerTrusted( java.security.cert.X509Certificate[] certs, String authType ) {	}
                }
        };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance( "SSL" );
            sc.init( null, trustAllCerts, new java.security.SecureRandom() );
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(
                    new HostnameVerifier() {
                        public boolean verify(String urlHostName, SSLSession session) {
                            return true;
                        }
                    });
        }
        catch ( Exception e ) {
            //We can not recover from this exception.
            e.printStackTrace();
        }
    }


}
