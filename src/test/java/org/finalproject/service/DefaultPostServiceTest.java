package org.finalproject.service;


import org.finalproject.entity.Post;
import org.finalproject.repository.PostJpaRepository;
import org.finalproject.repository.RepositoryInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultPostServiceTest {

    @Mock
    private PostJpaRepository postTestJpaRepository;


    @InjectMocks
    private GeneralService<Post> postService  = new GeneralService<Post>() {
    };

    @InjectMocks
    private DefaultPostService defaultPostService;

    @Captor
    private ArgumentCaptor<Post> postArgumentCaptor;

    @Test
    public void testGetAllPageble() {
        Post post = new Post();
        when(postTestJpaRepository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(post)));
        Pageable pageable = PageRequest.of(1, 2);
        List<Post> posts = postService.findAll(pageable).toList();

        assertEquals(post, posts.get(0));
    }

    @Test
    public void test_GetAll_Success() {
        Post post1 = new Post();
        Post post2 = new Post();
        List<Post> postsExpected = List.of(post1, post2);
        when(postTestJpaRepository.findAll())
                .thenReturn(postsExpected);

        List<Post> postsActual = postService.findAll();
        assertNotNull(postsActual);
        assertFalse(postsActual.isEmpty());
        assertIterableEquals(postsExpected, postsActual);
    }

    @Test
    public void test_Create_Success() {
        Post post1 = new Post();

        postService.save(post1);

        verify(postTestJpaRepository).save(postArgumentCaptor.capture());
        Post postActualArgument = postArgumentCaptor.getValue();
        assertEquals(post1,postActualArgument);
    }
    @Test
    public void test_Put_Success() {
        Post post1 = new Post();


        post1.setContent("Hy,friend!");
        post1.setCreatedDate(new Date());

        postService.save(post1);

        verify(postTestJpaRepository).save(postArgumentCaptor.capture());
        Post  postActualArgument = postArgumentCaptor.getValue();
        assertEquals(post1, postActualArgument);
    }
    @Test
    public void test_Delete_Success() {
        Post post1 = new Post();

        postService.save(post1);
        post1.setId(1L);

       post1.setContent("Hy,friend!");
        postService.delete(post1);

        verify(postTestJpaRepository).delete(postArgumentCaptor.capture());
        Post postActualArgument = postArgumentCaptor.getValue();
        assertEquals(post1, postActualArgument);
    }


}