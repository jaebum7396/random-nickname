package com.example.gitaction;



import com.example.gitaction.service.NicknameService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@RequiredArgsConstructor
public class TestController {
    private final NicknameService nicknameService;

    @GetMapping("/nickname") public List<String> getnickname(@RequestParam(value = "count",required = false) int count)
    {
        if(count==0) count=2;
        return  nicknameService.makeNickname(count);
    }
}


