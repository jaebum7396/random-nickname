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
        String url="https://ko.wiktionary.org/w/index.php?title=%EB%B6%84%EB%A5%98:%ED%95%9C%EA%B5%AD%EC%96%B4_%EB%AA%85%EC%82%AC&pagefrom=%EA%B4%80%EC%9A%A9%EC%96%B4#mw-pages";
        Connection conn = Jsoup.connect(url);
        Document html= conn.get();
        Elements fileblocks=html.getElementsByClass("vector-body");
        for(Element fileblock : fileblocks){
            Elements files=fileblock.getElementsByTag("li");
            for(Element elm : files) {
                String text = elm.text();
                //String href = elm.attr("title");
                System.out.println(text);
            }
        }
        return "success";
    }
}
