package com.domain.tracker.service;

import com.domain.tracker.model.Issue;
import com.domain.tracker.model.IssueMessage;
import com.domain.tracker.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueRepository issueRepository;

    public Iterable<Issue> issues() {
        return issueRepository.findAll();
    }

    public Issue create(Issue issue) {
        issue.setCreated(Timestamp.valueOf(LocalDateTime.now()));
        return issueRepository.save(issue);
    }

    public Issue update(int id, Issue issue) {
        Issue currentIssue = issueRepository.findById(id);

        currentIssue.setStatus(issue.getStatus());
        return issueRepository.save(currentIssue);
    }

    public void delete(int id) {
        issueRepository.deleteById(id);
    }

    public Issue read(int id) {
        return issueRepository.findById(id);
    }

    public void addMessage(int id, IssueMessage message) {
        Issue issue = issueRepository.findById(id);
        issue.setMessage(BigInteger.valueOf(message.getId()));
        issueRepository.save(issue);
    }

    public Iterable<Issue> filterIssuesByAssignee(BigInteger assignee) {
        return issueRepository.findByAssignee(assignee);
    }

    public Iterable<Issue> filterIssuesByReporterAndStatus(BigInteger reporter, String status) {
        return issueRepository.findByReporterAndStatus(reporter,status);
    }

    public Iterable<Issue> filterIssuesBetweenStartAndEndDate(Date startDate, Date endDate) {
        return issueRepository.findAllByCreatedBetween(startDate,endDate);
    }

    public Iterable<Issue> filterIssuesSortedByCreationDateAsc() {
        return issueRepository.findAllByOrderByCreatedAsc();
    }

    public Iterable<Issue> listAllWithPagination(int pageNum) {
        int pageSize = 5;

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        return issueRepository.findAll(pageable).getContent();
    }
}

