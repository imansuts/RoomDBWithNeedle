package vcx.admin.com.myroomdb.services;

import java.util.List;

import vcx.admin.com.myroomdb.db.User;

public interface UserService {

    List<User> getAll();

    void insert(User user);
}
