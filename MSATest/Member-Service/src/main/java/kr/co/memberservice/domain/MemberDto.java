package kr.co.memberservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MemberDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class CREATE{

        private String identity;
        private String password;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class LOGIN{
        private String identity;
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ{
        private String identity;
        private String name;
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class READ_BOARD{
        private String identity;
        private String name;
        private String title;
        private String content;
    }
}
