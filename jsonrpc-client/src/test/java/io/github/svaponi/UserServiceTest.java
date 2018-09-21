package io.github.svaponi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {

        User bob = userService.createUser("bob", "123");
        System.out.println("created bob: " + bob);

        assertThat(bob)
                .hasFieldOrPropertyWithValue("username", "bob")
                .hasFieldOrPropertyWithValue("password", "123");

        User alice = userService.createUser("alice", "456");
        System.out.println("created alice: " + alice);

        assertThat(alice)
                .hasFieldOrPropertyWithValue("username", "alice")
                .hasFieldOrPropertyWithValue("password", "456");

        int count = userService.getUserCount();

        assertThat(count).isEqualTo(2);

        User found = userService.findUserByUserName("bob");

        assertThat(found)
                .hasFieldOrPropertyWithValue("username", "bob")
                .hasFieldOrPropertyWithValue("password", "123");
    }

}
