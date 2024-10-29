package com.example.gitaction;


import com.example.gitaction.common.ListResult;
import com.example.gitaction.common.service.ResponseService;
import com.example.gitaction.service.NicknameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1")
@RequiredArgsConstructor
public class TestController {
    private final NicknameService nicknameService;
    private final ResponseService responseService;

   @GetMapping("/nickname") public ResponseEntity<ListResult<String>> getnickname(@RequestParam(defaultValue = "2")  int count) throws IOException {
        return  new ResponseEntity<>(responseService.getListResult(nicknameService.makeRandomNickname(count)), HttpStatus.OK);
    }
    @GetMapping("/character")
    public ResponseEntity<ListResult<String>> getCharacterNickname(@RequestParam(defaultValue = "2") int count) throws  IOException{
       return new ResponseEntity<>(responseService.getListResult(nicknameService.makeCharterNickname(count)),HttpStatus.OK);
    }


}


