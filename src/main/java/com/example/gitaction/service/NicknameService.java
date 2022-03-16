package com.example.gitaction.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.shuffle;

@Service
public class NicknameService {
      String noun[] = new String[]{"사과","대추","아이패드","대통령","마우스","대벌레","고양이","제이지","로션","탱크"};
      String adjective[] = new String[]{"맛있는","멋진","사악한","매운","떫은","군침싹도는","마약한","민망한","표독스러운"};
    public List<String> makeNickname(int count){
        List<String> nsa = new ArrayList<>();
        for(int i=0;i<count;i++){
            shuffle(Arrays.asList(noun));
            shuffle(Arrays.asList(adjective));
            nsa.add(adjective[i]+" "+noun[i]);
        }
        return nsa;
    }

}
