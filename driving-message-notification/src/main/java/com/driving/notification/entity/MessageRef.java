package com.driving.notification.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document(collection = "message_ref")
public class MessageRef implements Serializable {
    @Id
    private String _id;

    @Indexed
    private String messageId;

    @Indexed
    private Long receiverId;

    private String receiverIdentity;

    @Indexed
    private Boolean readFlag;

    @Indexed
    private Boolean lastFlag;
}
