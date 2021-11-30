package com.example.arsenalfinalproject.web;

import com.example.arsenalfinalproject.model.entity.CommentEntity;
import com.example.arsenalfinalproject.model.entity.NewsEntity;
import com.example.arsenalfinalproject.model.entity.PictureEntity;
import com.example.arsenalfinalproject.model.entity.UserEntity;
import com.example.arsenalfinalproject.repository.CommentRepository;
import com.example.arsenalfinalproject.repository.NewsRepository;
import com.example.arsenalfinalproject.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentRestControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;


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
        userRepository.deleteAll();
        newsRepository.deleteAll();
    }

    @Test
    void testGetComments() {
        long newsId = initNews();


    }

    private Long initNews() {
        NewsEntity testNews = new NewsEntity();

        testNews.setTopic("Topic news ");
        testNews.setLocalDateNews(LocalDate.parse("2004-01-22"));
        testNews.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        PictureEntity n = new PictureEntity();
        n.setUrl("aaaaaaaaa");
        n.setPublicId("12342142342");

        testNews.setPicture(n);

        testNews = newsRepository.save(testNews);

        CommentEntity coment1 = new CommentEntity();
        coment1.setCreated(LocalDateTime.now());
        coment1.setAuthor(testUser);
        coment1.setTextContent("comment1");
        coment1.setApproved(true);
        coment1.setNews(testNews);


        CommentEntity coment2 = new CommentEntity();
        coment2.setCreated(LocalDateTime.now());
        coment2.setAuthor(testUser);
        coment2.setTextContent("comment2");
        coment2.setApproved(true);
        coment2.setNews(testNews);

        testNews.setComments(List.of(coment1, coment2));

        return newsRepository.save(testNews).getId();

    }

///asdasdasdasd
}
