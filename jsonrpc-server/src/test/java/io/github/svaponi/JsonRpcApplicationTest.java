package io.github.svaponi;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;
import io.github.svaponi.User;
import io.github.svaponi.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonRpcApplicationTest {

    @LocalServerPort
    int port;

    @Test
    public void contextLoads() throws MalformedURLException {

        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("http://localhost:" + port + "/UserService.json"));

        UserService userService = ProxyUtil.createClientProxy(
                getClass().getClassLoader(),
                UserService.class,
                client);

        User bob = userService.createUser("bob", "123");

        assertThat(bob)
                .hasFieldOrPropertyWithValue("username", "bob")
                .hasFieldOrPropertyWithValue("password", "123");

        User alice = userService.createUser("alice", "456");

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
