package com.example.gitaction.service;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.shuffle;

@Service
public class NicknameService {



      static String noun[] = new String[]{"사과","대추","아이패드","대통령","마우스","대벌레","고양이","제이지","로션","탱크"};
      static String adjective[] = new String[]{"맛있는","멋진","사악한","매운","떫은","군침싹도는","마약한","민망한","표독스러운"};
    public List<String> makeNickname(int count){
        List<String> nsa = new ArrayList<>();
        for(int i=0;i<count;i++){
            shuffle(Arrays.asList(noun));
            shuffle(Arrays.asList(adjective));
            nsa.add(adjective[i]+" "+noun[i]);
        }
        return nsa;
    }

    public String getCraw() throws IOException  {
        String[] words  = {"가","거","고","구","그","기","까","나","너","노","누","느","니","다","더","도","두","드","디","따","라",
                "러","로","루","르","리","마","머","모","무","므","미","바","버","보","부","브","비","빠","사","서","소","수","스",
                "시","싸","아","어","오","우","으","이","자","저","조","주","즈","지","짜","차","처","초","추","츠","치",
                "카","커","코","쿠","크","키","타","터","토","투","트","티","파","퍼","포","푸","프","피","하","허",
                "호","후","흐","히"};
        for(int i=0; i< words.length;i++) {
            String url = "https://ko.wiktionary.org/w/index.php?title=분류:한국어_명사&from=";
            Connection conn = Jsoup.connect(url+words[i]);
            Document html = conn.get();
            Elements fileblocks = html.getElementsByClass("mw-category-group");
            for (Element fileblock : fileblocks) {
                Elements files = fileblock.getElementsByTag("ul");
                for (Element elm : files) {
                    Elements text = elm.getElementsByTag("a");
                    for (Element asd : text) {
                        String tt = asd.attr("title");
                        if ((tt.length() < 5)&&(tt.length()>1))
                            System.out.println(tt);
                    }
                }
            }
        }
        return "success";
    }
}
