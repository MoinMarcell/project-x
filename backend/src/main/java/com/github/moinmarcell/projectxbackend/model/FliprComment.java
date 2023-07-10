package com.github.moinmarcell.projectxbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "flipr_comment")
public class FliprComment {

    @MongoId
    private String id;

    private String content;

    @DBRef(db = "flipr_user")
    private FliprUser author;

    private String createdAt;
    private String updatedAt;

    @DBRef(db = "flipr")
    private Flipr flipr;

}
