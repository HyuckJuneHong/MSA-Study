package kr.co.memberservice.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "board-service")        //호출할 마이크로 서비스 명
public interface BoardFeignClient {

    @GetMapping("/actuator/info/board/read") //해당 주소 호출
    Map<String, String> getBoards(@RequestParam("identity") String identity);
}
