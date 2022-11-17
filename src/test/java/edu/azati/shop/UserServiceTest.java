package edu.azati.shop;

import edu.azati.shop.entity.User;
import edu.azati.shop.enums.UserRole;
import edu.azati.shop.services.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @Mock
    UserService userService;

    @Test
    public void userServiceAddUserTest() {
        User user1 = new User().withId(6).withName("fwef").withSurname("tjyy").withPatronymic("wfewf").withUserRole(UserRole.Customer);
        doNothing().when(userService).addUser(isA(User.class));
        userService.addUser(user1);
        verify(userService, times(1)).addUser(user1);

    }

    @Test
    public void userServiceAddUsersTest() {
        User user1 = new User().withId(6).withName("fwef").withSurname("tjyy").withPatronymic("wfewf").withUserRole(UserRole.Customer);
        User user2 = new User().withId(6).withName("fwef").withSurname("tjyy").withPatronymic("wfewf").withUserRole(UserRole.Customer);
        List<User> users = List.of(user1, user2);
        doNothing().when(userService).addUsers(isA(List.class));
        userService.addUsers(users);
        verify(userService, times(1)).addUsers(users);

    }

    @Test
    public void userServiceGetAllUsersTest() {
        User mockUser = mock(User.class);
        List<User> users = userService.getAllUsers();
        users.add(mockUser);
        when(userService.getAllUsers()).thenReturn(users);
        Assert.assertEquals(1, users.size());

    }

    @Test
    public void userServiceGetUserByIdTest() {
        User user = new User(7, "asd", "zxc", "dwdq","wejfowe", "qwmdo", UserRole.Customer, null);
        when(userService.getUserById(7)).thenReturn(user);
        Assert.assertEquals(7, user.getId());
    }

    @Test
    public void userServiceGetUserByNameTest() {
        User user = new User(7, "asd", "zxc", "dwdq","wejfowe", "qwmdo", UserRole.Customer, null);
        when(userService.getUserByName("asd")).thenReturn(user);
        Assert.assertEquals("asd", user.getName());
    }

    @Test
    public void userServiceGetUserBySurnameTest() {
        User user = new User(7, "asd", "zxc", "dwdq","wejfowe", "qwmdo", UserRole.Customer, null);
        when(userService.getUserBySurname("zxc")).thenReturn(user);
        Assert.assertEquals("zxc", user.getSurname());
    }

    @Test
    public void userServiceDeleteUserTest() {
        doNothing().when(userService).deleteUserById(isA(Long.class));
        userService.deleteUserById(anyLong());
        verify(userService, times(1)).deleteUserById(anyLong());

    }

    @Test
    public void userServiceUpdateUserTest() {
        doNothing().when(userService).updateUser(isA(Long.class), isA(String.class),isA(String.class),isA(String.class),isA(UserRole.class));
        userService.updateUser(19, "zxc", "wefl", "ektp", UserRole.Customer);
        verify(userService, times(1)).updateUser(19, "zxc", "wefl", "ektp", UserRole.Customer);

    }

}
