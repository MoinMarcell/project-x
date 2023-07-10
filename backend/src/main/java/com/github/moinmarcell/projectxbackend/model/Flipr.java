package com.github.moinmarcell.projectxbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "flipr")
public class Flipr {

    @MongoId
    private String id;

    private String content;

    @DBRef(db = "flipr_user")
    private FliprUser author;

    private String createdAt;
    private String updatedAt;

    @DBRef(db = "flipr_tag")
    private List<FliprTags> tags;

    @DBRef(db = "flipr_user")
    private List<FliprUser> likes;

    @DBRef(db = "flipr_comment")
    private List<FliprComment> comments;

    private String image;

}
