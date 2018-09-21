package io.github.svaponi;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class PlainJavaClient {

    static int serverPort = 8080;
    static String serverUrl = "http://localhost:" + serverPort;

    public static void main(String[] a) throws MalformedURLException {

        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL(serverUrl + "/UserService.json"));

        UserService userService = ProxyUtil.createClientProxy(
                PlainJavaClient.class.getClassLoader(),
                UserService.class,
                client);

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
