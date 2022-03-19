package com.example.gitaction.service;


import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.shuffle;

@Service
@Slf4j
public class NicknameService {

      //초기 버전의 닉네임
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
    //랜덤이 강한 닉네임 기능
    public static List<String> makeRandomNickname(int count) throws IOException {
        List<String> nickName = new ArrayList<>();
        String nounWords[]=getResource("noun.txt").split(",");
        String adjWords[]=getResource("adjective.txt").split(",");
        if (count>10)
            count=10;
        return  loopWord(count,nickName,nounWords,adjWords);
    }


    public static List<String> loopWord(int count,List<String> nickName,String[] Noun,String[] Adj){
        for(int i=0;i<count;i++) {
            int firstWord = (int) (Math.random() * (16720 + 1));
            int secondWord=(int) (Math.random() * (16721 + 1));
            int adjective=(int) (Math.random() * (165 + 1));
            nickName.add(getPostWord(Adj[adjective] + Noun[firstWord],"과","와")
                    +" "+ Noun[secondWord]);
            log.info("1st: {},2st: {}, 3st: {}",firstWord,secondWord,adjective);
        }
        return nickName;
    }
    public static String [] getWord(ClassPathResource url) throws IOException {
        FileInputStream fileStream = null; // 파일 스트림
        Path path = Paths.get(url.getURI());
        fileStream = new FileInputStream(String.valueOf(path));// 파일 스트림 생성
        //버퍼 선언
        byte[ ] readBuffer = new byte[fileStream.available()];
        while (fileStream.read( readBuffer ) != -1){}
        fileStream.close(); //스트림 닫기
        String word=new String(readBuffer);
        return word.split(",");
    }
    public static String getResource(String filename) throws IOException {
        ClassPathResource Path = new ClassPathResource(filename);
        InputStream inputStream = new BufferedInputStream(Path.getInputStream());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
        String streamToString = streamOfString.collect(Collectors.joining());
        return streamToString;
    }
    public static String getPostWord(String str, String firstVal, String secondVal) {

        try {
            char laststr = str.charAt(str.length() - 1);
            // 한글의 제일 처음과 끝의 범위밖일 경우는 오류
            if (laststr < 0xAC00 || laststr > 0xD7A3) {
                return str;
            }

            int lastCharIndex = (laststr - 0xAC00) % 28;

            // 종성인덱스가 0이상일 경우는 받침이 있는경우이며 그렇지 않은경우는 받침이 없는 경우
            if(lastCharIndex > 0) {
                // 받침이 있는경우
                // 조사가 '로'인경우 'ㄹ'받침으로 끝나는 경우는 '로' 나머지 경우는 '으로'
                if(firstVal.equals("으로") && lastCharIndex == 8) {
                    str += secondVal;
                } else {
                    str += firstVal;
                }
            } else {
                // 받침이 없는 경우
                str += secondVal;
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return str;
    }

    public String getCraw() throws IOException  {
        String[] words  = {"가","거","고","구","그","기","까","나","너","노","누","느","니","다","더","도","두","드","디","따","라",
                "러","로","루","르","리","마","머","모","무","므","미","바","버","보","부","브","비","빠","사","서","소","수","스",
                "시","싸","아","어","오","우","으","이","자","저","조","주","즈","지","짜","차","처","초","추","츠","치",
                "카","커","코","쿠","크","키","타","터","토","투","트","티","파","퍼","포","푸","프","피","하","허",
                "호","후","흐","히"};
        for(int i=0; i< words.length;i++) {
            String url = "https://ko.wiktionary.org/w/index.php?title=분류:한국어_관형사형(형용사)&from=";
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
