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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "flipr_user")
public class FliprUser {

    @MongoId
    private String id;

    private String username;
    private String email;
    private String password;
    private String createdAt;
    private String updatedAt;
    private String image;

    @DBRef(db = "flipr")
    private List<Flipr> fliprs;

}
