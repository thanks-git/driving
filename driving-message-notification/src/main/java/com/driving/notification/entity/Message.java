package com.driving.notification.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @author YueLiMin
 */
@Data
@Document(collection = "message")
public class Message implements Serializable {
    @Id
    private String _id;

    @Indexed(unique = true)
    private String uuid;

    @Indexed
    private Long senderId;

    private String senderIdentity;

    // https://img.bosszhipin.com/beijin/upload/avatar/20220423/607f1f3d68754fd0a64ade57d3410fcf25d0375444ddb22eaaa66a784d8588a7a4352d5b90d74a2a_s.png
    private String senderPhoto;

    private String senderName;

    private String msg;

    @Indexed
    private Date sendTime;
}
