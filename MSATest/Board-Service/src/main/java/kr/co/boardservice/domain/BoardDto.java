package kr.co.boardservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class BoardDto {

    @Builder
    @Getter
    @AllArgsConstructor
    public static class CREATE{
        private String title;
        private String content;
        private String identity;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    public static class READ{
        private String title;
        private String content;
        private String identity;
    }
}
