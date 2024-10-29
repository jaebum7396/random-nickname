package com.random.controller;


import com.random.common.ListResult;
import com.random.common.service.ResponseService;
import com.random.service.RandomNicknameService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RequestMapping(path = "/random/api/v1")
@Api(tags = "RandomNickNameController")
@Tag(name = "RandomNickNameController", description = "RandomNickNameController")
@RestController
@RequiredArgsConstructor
public class RandomNickNameController {
    private final RandomNicknameService randomNicknameService;
    private final ResponseService responseService;

   @GetMapping("/nickname") public ResponseEntity<ListResult<String>> getnickname(@RequestParam(defaultValue = "2")  int count) throws IOException {
        return  new ResponseEntity<>(responseService.getListResult(randomNicknameService.makeRandomNickname(count)), HttpStatus.OK);
    }
    @GetMapping("/character")
    public ResponseEntity<ListResult<String>> getCharacterNickname(@RequestParam(defaultValue = "2") int count) throws  IOException{
       return new ResponseEntity<>(responseService.getListResult(randomNicknameService.makeCharterNickname(count)),HttpStatus.OK);
    }


}


