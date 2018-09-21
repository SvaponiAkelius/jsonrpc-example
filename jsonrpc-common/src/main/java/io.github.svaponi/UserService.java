package io.github.svaponi;

public interface UserService {
    User createUser(String username, String password);
    User findUserByUserName(String username);
    int getUserCount();
}
