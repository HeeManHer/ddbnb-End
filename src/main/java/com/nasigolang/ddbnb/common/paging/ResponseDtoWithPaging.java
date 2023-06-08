package com.nasigolang.ddbnb.common.paging;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ResponseDtoWithPaging {

    private Object data;
    private SelectCriteria pageInfo;
}
