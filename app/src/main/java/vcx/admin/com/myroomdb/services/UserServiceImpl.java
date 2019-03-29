package vcx.admin.com.myroomdb.services;

import android.content.Context;

import java.util.List;

import vcx.admin.com.myroomdb.application.RoomDB;
import vcx.admin.com.myroomdb.db.User;
import vcx.admin.com.myroomdb.db.UserDao;

public class UserServiceImpl implements UserService {

    private UserDao userDao;
    private Context context;

    public UserServiceImpl(Context context) {
        this.context = context;
        userDao = RoomDB.getInstance().getAppDatabase().userDao();
    }

    @Override
    public List<User> getAll() {
        return userDao.loadAll();
    }

    @Override
    public void insert(User user) {
        userDao.insert(user);
    }
}
