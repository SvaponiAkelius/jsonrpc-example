package io.github.svaponi;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {

    final Set<User> database = new HashSet<>();

    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        database.add(user);
        System.out.println("created new user: " + username);
        return user;
    }

    public User findUserByUserName(String username) {
        return database.stream().filter(it -> it.getUsername().equals(username)).findAny().orElse(null);
    }

    public int getUserCount() {
        return database.size();
    }
}
