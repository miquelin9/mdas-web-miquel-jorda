package com.ccm.user.user.domain.interfaces;

import com.ccm.user.user.domain.aggregate.User;
import com.ccm.user.user.domain.vo.UserId;
import com.ccm.user.user.domain.vo.UserName;

import java.util.List;

public interface UserRepository {

    public User find(UserId userId);

    public boolean exists(UserId userId);

    public List<User> search(UserName userName);

    public void create(User user);

    public User update(User user);

    public void delete(User user);

    public void deleteAll();
}
