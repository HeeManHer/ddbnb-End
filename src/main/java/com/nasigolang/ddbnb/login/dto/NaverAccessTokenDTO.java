package com.nasigolang.ddbnb.login.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverAccessTokenDTO {

    private long memberId;
    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
    private int refresh_token_expires_in;
    private String loginType;

}
