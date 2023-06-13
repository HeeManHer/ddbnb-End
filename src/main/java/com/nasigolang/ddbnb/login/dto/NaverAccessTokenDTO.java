package com.nasigolang.ddbnb.login.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class NaverAccessTokenDTO {

    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
    private String error;
    private String error_description;
    private int refresh_token_expires_in;

}
