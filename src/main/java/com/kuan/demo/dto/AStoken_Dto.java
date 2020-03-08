package com.kuan.demo.dto;

import lombok.Data;

@Data
public class AStoken_Dto {
    private String client_id;
    private String Client_secret;
    private String code;
    private String redirect_url;
    private String state;


}
