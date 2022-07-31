package cn.beckbi.service.impl;

import cn.beckbi.dao.UserMapper;
import cn.beckbi.model.User;
import cn.beckbi.service.UserService;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @program: spring-cloud
 * @description:
 * @author: bikang
 * @create: 2022-07-31 17:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * master获取数据
     * @param id Long
     * @return User
     */
    @Override
    public User getByIdFromMaster(Long id) {

        return Optional.ofNullable(id).map(uid -> {
            User user = null;
            HintManager.clear();
            try (HintManager hintManager = HintManager.getInstance()) {
                hintManager.setMasterRouteOnly();
                user = userMapper.getUserById(uid);
            }
            return user;
        }).orElse(null);
    }

    /**
     * slave获取数据
     * @param id Long
     * @return User
     */
    @Override
    public User getByIdFromSlave(Long id) {
        return userMapper.getUserById(id);
    }
}
