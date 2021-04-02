package com.domain.tracker.controller;

import com.domain.tracker.model.Issue;
import com.domain.tracker.model.IssueMessage;
import com.domain.tracker.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.Date;

@RestController
@RequestMapping("/issues")
public class IssuesController {

    @Autowired
    private IssueService issueService;

    @GetMapping
    private ResponseEntity<Iterable<Issue>> list() {
        Iterable<Issue> issues = issueService.issues();
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/filterByAssignee/{id}")
    private ResponseEntity<Iterable<Issue>> getIssuesByAssignee(@PathVariable int id) {
        Iterable<Issue> issues = issueService.filterIssuesByAssignee(BigInteger.valueOf(id));
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/filterByReporterAndStatus/{id}/{status}")
    private ResponseEntity<Iterable<Issue>> getIssuesByReporterAndStatus(@PathVariable int id, @PathVariable String status) {
        Iterable<Issue> issues = issueService.filterIssuesByReporterAndStatus(BigInteger.valueOf(id), status);
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/filterBetween/{startDate}/{endDate}")
    private ResponseEntity<Iterable<Issue>> getIssuesBetweenStartAndEndDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        Iterable<Issue> issues = issueService.filterIssuesBetweenStartAndEndDate(startDate, endDate);
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/sortByCreated")
    private ResponseEntity<Iterable<Issue>> getIssuesSortedByCreatedDateAsc() {
        Iterable<Issue> issues = issueService.filterIssuesSortedByCreationDateAsc();
        return ResponseEntity.ok(issues);
    }

    @GetMapping("/withPagination/{num}")
    private ResponseEntity<Iterable<Issue>> listAllWithPagination(@PathVariable int num) {
        Iterable<Issue> issues = issueService.listAllWithPagination(num);
        return ResponseEntity.ok(issues);
    }

    @PostMapping
    private ResponseEntity<Issue> create(@RequestBody Issue issue) {
        Issue saved = issueService.create(issue);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/{id}")
    private ResponseEntity<Issue> read(@PathVariable String id) {
        Issue read = issueService.read(Integer.parseInt(id));
        return ResponseEntity.ok(read);
    }

    @PutMapping("/{id}")
    private ResponseEntity<Issue> update(@PathVariable int id, @RequestBody Issue issue) {
        Issue updated = issueService.update(id, issue);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity delete(@PathVariable int id) {
        issueService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/message")
    private ResponseEntity addMessage(@PathVariable int id, @RequestBody IssueMessage message) {
        issueService.addMessage(id, message);
        return ResponseEntity.ok().build();
    }
}
