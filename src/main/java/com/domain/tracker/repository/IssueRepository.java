package com.domain.tracker.repository;

import com.domain.tracker.model.Issue;
import com.domain.tracker.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Integer> {
    Issue findById(int id);

    void deleteById(int id);

    List<Issue> findByAssignee(BigInteger assignee);

    List<Issue> findByReporterAndStatus(BigInteger reporter, String status);

    List<Issue> findAllByCreatedBetween(Date startDate, Date endDate);

    List<Issue> findAllByOrderByCreatedAsc();

    Page<Issue> findAll(Pageable pageable);
}
