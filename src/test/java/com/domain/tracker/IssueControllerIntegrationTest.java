package com.domain.tracker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.domain.tracker.model.Issue;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigInteger;
import java.util.Date;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TrackerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IssueControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllIssues() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/issues",
                HttpMethod.GET, entity, String.class);

        assertNotNull(response.getBody());
    }

    @Test
    public void testGetIssueById() {
        Issue issue = restTemplate.getForObject(getRootUrl() + "/issues/5", Issue.class);
        assertNotNull(issue);

        assertEquals(issue.getTitle(), "Issue.java number five");
        assertEquals(issue.getDescription(), "This is issue number five");
        assertEquals(issue.getMessage(), null);
        assertEquals(issue.getStatus(), "in_progress");
        assertEquals(issue.getAssignee(), BigInteger.ONE);
        assertEquals(issue.getReporter(), BigInteger.ONE);
    }

    @Test
    public void testCreateIssue() {
        Issue issue = new Issue();
        issue.setCreated(new Date());
        issue.setStatus("in_progress");
        issue.setDescription("This is issue number six");
        issue.setReporter(BigInteger.TWO);
        issue.setTitle("Issue.java number six");

        ResponseEntity<Issue> postResponse = restTemplate.postForEntity(getRootUrl() + "/issues", issue, Issue.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());

        assertEquals(postResponse.getBody().getDescription(), "This is issue number six");
    }

    @Test
    public void testUpdateEmployee() {
        int id = 2;
        Issue issue = restTemplate.getForObject(getRootUrl() + "/issues/" + id, Issue.class);
        issue.setTitle("Updated - Issue.java number two");

        restTemplate.put(getRootUrl() + "/employees/" + id, issue);

        Issue updatedIssue = restTemplate.getForObject(getRootUrl() + "/issues/" + id, Issue.class);
        assertNotNull(updatedIssue);
    }

    @Test
    public void testDeleteIssue() {
        int id = 3;
        Issue issue = restTemplate.getForObject(getRootUrl() + "/issues/" + id, Issue.class);
        assertNotNull(issue);

        restTemplate.delete(getRootUrl() + "/issues/" + id);

        try {
            issue = restTemplate.getForObject(getRootUrl() + "/issues/" + id, Issue.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    public void testGetIssueById_NotFound() {
        Issue issue = restTemplate.getForObject(getRootUrl() + "/issues/6", Issue.class);
        assertNull(issue);
    }
}
