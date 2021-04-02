package com.domain.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "MESSAGE", schema = "ISSUE_TRACKER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IssueMessage extends BaseEntity {

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private BigInteger issue;

    @Column(nullable = false)
    private BigInteger user;
}

