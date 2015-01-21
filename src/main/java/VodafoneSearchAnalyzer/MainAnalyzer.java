package VodafoneSearchAnalyzer;

import java.util.List;

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
        List<String> wordsToBeSearched = SearchWordsProvider.getWordsToBeSearched();
//        VodafoneSearchAnalyzer.OverAllSeeker seeker = new VodafoneSearchAnalyzer.OverAllSeeker(new VodafoneSearchAnalyzer.Seekers.VodafoneSelfServiceSeeker(5), new VodafoneSearchAnalyzer.Seekers.VodafoneCareCenterSeeker(5), new VodafoneSearchAnalyzer.Seekers.VodafoneOfferSeeker(5), new VodafoneSearchAnalyzer.Seekers.VodafoneWorldManualsSeeker(5), new VodafoneSearchAnalyzer.Seekers.VodafoneOverAllSeeker(10));
//        List<VodafoneSearchAnalyzer.AbstractSearchResult> results = seeker.searchForWords(wordsToBeSearched);
//        for (String word : wordsToBeSearched) {
//            System.out.println(word);
//        }
//        for (VodafoneSearchAnalyzer.AbstractSearchResult result : results) {
//            System.out.println(result.toString());
//        }
    }

}
