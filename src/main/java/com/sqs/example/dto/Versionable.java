package com.sqs.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Versionable {

    Integer version;

    Long id;

    String simpleName;

    String newValueJson;

    String createdBy;

    String modifiedBy;

    LocalDateTime createdOn;

    LocalDateTime updatedOn;

}
