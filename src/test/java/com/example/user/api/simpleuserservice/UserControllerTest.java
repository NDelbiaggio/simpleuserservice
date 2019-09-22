package com.example.user.api.simpleuserservice;

import com.example.user.api.simpleuserservice.exceptions.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IUserService<User, String> userService;

    private User user;
    ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception {
        user = new User("id_1","user_1", "group_1");
    }

    @Test
    public void should_find_all_users_success() throws Exception {
        List<User> allUsers = singletonList(user);
        given(userService.findAll()).willReturn(allUsers);
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(user.getName())));
    }

    @Test
    public void should_find_user_by_id_succes() throws Exception {
        given((userService.findById(user.getId()))).willReturn(user);
        mockMvc.perform(get("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.groupId", is(user.getGroupId())));
    }

    @Test
    public void should_404_find_by_id_if_user_not_found() throws Exception {
        given((userService.findById(anyString()))).willThrow(new EntityNotFoundException("not_exist",User.class));
        mockMvc.perform(get("/users/not_exist"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_save_user_success() throws Exception {
        given((userService.save(user))).willReturn(user);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.groupId", is(user.getGroupId())));
    }

    @Test
    public void should_400_save_with_no_body() throws Exception {
        mockMvc.perform(post("/users")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(userService, never()).save(user);
    }

    @Test
    public void should_400_save_with_empty_groupid() throws Exception {
        user.setGroupId("");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(userService, never()).save(user);
    }

    @Test
    public void should_400_save_with_empty_id() throws Exception {
        user.setId("");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(userService, never()).save(user);
    }

    @Test
    public void should_400_save_with_empty_name() throws Exception {
        user.setName("");
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
        verify(userService, never()).save(user);
    }


    @Test
    public void should_delete_user_success() throws Exception {
        given((userService.delete(user.getId()))).willReturn(user);
        mockMvc.perform(delete("/users/" + user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.getId())))
                .andExpect(jsonPath("$.name", is(user.getName())))
                .andExpect(jsonPath("$.groupId", is(user.getGroupId())));
    }

    @Test
    public void should_404_delete_user_not_found() throws Exception {
        given((userService.delete(user.getId()))).willThrow(new EntityNotFoundException(user.getId(), User.class));
        mockMvc.perform(delete("/users/" + user.getId()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void should_save_all_users_success() throws Exception {
        User user2 = new User("id_2","user_2","group_2");
        List<User> userList = Arrays.asList(new User[]{user, user2});
        given((userService.saveAll(userList))).willReturn(userList);
        mockMvc.perform(post("/users/several")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(userList))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void should_get_groups_by_group_id_success() throws Exception {
        Map<String, List<User>> map = new HashMap<>();
        map.put(user.getGroupId(), Arrays.asList(new User[]{user}));
        given((userService.findAllGroupByGroupId())).willReturn(map);
        mockMvc.perform(get("/users/groups"))
                .andExpect(status().isOk());
    }
}