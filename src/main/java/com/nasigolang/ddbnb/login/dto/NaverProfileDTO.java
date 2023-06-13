package com.nasigolang.ddbnb.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverProfileDTO {
    private String resultcode;
    private String message;
    private Response response;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public class Response {
        private String id;
        private String nickname;
        private String profile_image;
        private String gender;

    }
}
