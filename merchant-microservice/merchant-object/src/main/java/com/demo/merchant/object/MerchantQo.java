package com.demo.merchant.object;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangyueming
 */
@Data
public class MerchantQo extends PageQo implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String linkman;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;

    private String userName;
    private String passWord;

}
