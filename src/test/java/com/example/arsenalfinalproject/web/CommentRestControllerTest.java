package com.example.arsenalfinalproject.web;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.arsenalfinalproject.model.binding.NewCommentBindingModel;
import com.example.arsenalfinalproject.model.entity.CommentEntity;
import com.example.arsenalfinalproject.model.entity.NewsEntity;
import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.repository.CommentRepository;
import com.example.arsenalfinalproject.repository.NewsRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.text.MatchesPattern;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.client.RequestMatcher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@WithMockUser("Mario")
@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {


    private static final String COMMENT_1 = "something  , something";
    private static final String COMMENT_2 = "something elsee , something";


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private UserEntity testUser;

    @BeforeEach
    void SetUp() {
        testUser = new UserEntity();
        testUser.setPassword("12345");
        testUser.setUsername("Mario");
        testUser.setFirstName("Maraaa");
        testUser.setLastName("Vladimirov");
        testUser.setEmail("admin@arsenal-bulgaria.com");
        testUser.setDateBirth(LocalDate.parse("2004-01-22"));


        testUser = userRepository.save(testUser);
    }

    @AfterEach
    void tearDown() {
        newsRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testGetComments() throws Exception {
        var news = initComments(initNewEntity());

        mockMvc.perform(get("/api/" + news.getId() + "/comments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$" , hasSize(2))).
                andExpect(jsonPath("$.[0].message", is(COMMENT_1))).
                andExpect(jsonPath("$.[1].message", is(COMMENT_2)));

    }

    @Test
    void testCreate() throws Exception {
        NewCommentBindingModel testComment = new NewCommentBindingModel()
                .setMessage(COMMENT_1);



                var emptyNews = initNewEntity();

        mockMvc.perform(
                post("/api/" + emptyNews.getId() + "/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testComment))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf())
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(header().string("Location", MatchesPattern.matchesPattern("/api/" + emptyNews.getId() + "/comments/\\d")))
                .andExpect(jsonPath("$.message").value(is(COMMENT_1)));

    }

    private NewsEntity initNewEntity() {
        NewsEntity testNews = new NewsEntity();

        testNews.setTopic("Topic news ");
        testNews.setLocalDateNews(LocalDate.parse("2004-01-22"));
        testNews.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        return newsRepository.save(testNews);

    }

    private NewsEntity initComments(NewsEntity newsEntity) {


        CommentEntity coment1 = new CommentEntity();
        coment1.setCreated(LocalDateTime.now());
        coment1.setAuthor(testUser);
        coment1.setTextContent(COMMENT_1);
        coment1.setApproved(true);
        coment1.setNews(newsEntity);

        CommentEntity coment2 = new CommentEntity();
        coment2.setCreated(LocalDateTime.now());
        coment2.setAuthor(testUser);
        coment2.setTextContent(COMMENT_2);
        coment2.setApproved(true);
        coment2.setNews(newsEntity);

        newsEntity.setComments(List.of(coment1, coment2));

        return newsRepository.save(newsEntity);

    }

///asdasdasdasd
}
