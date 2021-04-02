package com.domain.tracker.model;

import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ISSUE", schema = "ISSUE_TRACKER")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Issue extends BaseEntity {

//    @JoinColumn
//    @ManyToOne(targetEntity = User.class)
//    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigInteger reporter;

    @Column
    private BigInteger assignee;

    @Column(nullable = false)
    private Date created;

    @Column
    private Date completed;

    @Column
    private BigInteger message;

//    @JoinColumn(name = "id")
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = IssueMessage.class)
//    private List<IssueMessage> messages;

//    public enum Status {
//        ADDED, READY, ONGOING
//    }
}