package com.demo.merchant.object;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yangyueming
 */
@Data
public class KindQo extends PageQo implements Serializable {
    private Long id;
    private String name;
    private String link;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date created;


}
