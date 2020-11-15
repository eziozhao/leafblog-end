package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.bo.MyUserDetails;
import com.eziozhao.leafblog.common.ExceptionCast;
import com.eziozhao.leafblog.dao.UserDao;
import com.eziozhao.leafblog.dao.UserResourceDao;
import com.eziozhao.leafblog.dto.LoginParam;
import com.eziozhao.leafblog.dto.RegisterParam;
import com.eziozhao.leafblog.mbg.entity.*;
import com.eziozhao.leafblog.mbg.mapper.RolesUserMapper;
import com.eziozhao.leafblog.mbg.mapper.UserMapper;
import com.eziozhao.leafblog.service.UserService;
import com.eziozhao.leafblog.util.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author eziozhao.
 * @data 2020/6/4.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolesUserMapper rolesUserMapper;
    @Autowired
    private UserResourceDao userResourceDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public User register(RegisterParam registerParam) {
        //先判断用户是否存在
        User userExist = getUserByUsername(registerParam.getUsername());
        if (userExist != null) {
            ExceptionCast.cast("用户名已占用");
        }
        User user = new User();
        //拷贝参数到user
        BeanUtils.copyProperties(registerParam, user);
        user.setRegtime(new Date());
        //加密处理
        user.setPassword(passwordEncoder.encode(registerParam.getPassword()));
        userDao.insertReturnId(user);
        RolesUser rolesUser = new RolesUser();
//        新注册用户默认为普通用户
        rolesUser.setRid(2);
        rolesUser.setUid(user.getId());
        rolesUserMapper.insert(rolesUser);
        return user;
    }

    @Override
    public String login(LoginParam loginParam) {
        String token = null;
        User user = getUserByUsername(loginParam.getUsername());
        if (user == null) {
            ExceptionCast.cast("用户不存在");
        }
        if (user.getEnabled() == 0) {
            ExceptionCast.cast("用户已停用");
        }
        UserDetails userDetails = loadUserByUsername(loginParam.getUsername());
        if (!passwordEncoder.matches(loginParam.getPassword(), userDetails.getPassword())) {
            ExceptionCast.cast("用户名或密码错误");
        }
        //初始化一个已经认证的Token实例
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        //SecurityContextHolder 中持有的是当前用户的 SecurityContext，
        //而 SecurityContext 持有的是代表当前用户相关信息的 Authentication 的引用
        // 将 Authentication 对象赋给当前的 SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        token = jwtUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public int updatePassword(String oldPassword, String newPassword) {
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
            return -2;
        }
        if (!passwordEncoder.matches(oldPassword, getCurrentUser().getPassword())) {
            //旧密码错误
            return -1;
        }
        User user = new User();
        user.setId(getCurrentUser().getId());
        user.setPassword(passwordEncoder.encode(newPassword));
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = getUserByUsername(username);
        if (user != null) {
            List<Resource> resourceList = getUserResource(user.getId());
            return new MyUserDetails(user, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    @Override
    public User getCurrentUser() {
        MyUserDetails userDetails =
                (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public List<Menu> getMenuListByUserId(Integer id) {
        return userDao.getMenuListByUserId(id);
    }

    @Override
    public User fetchUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User getUserByUsername(String username) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(username);
        List<User> userList = userMapper.selectByExample(userExample);
        if (userList != null && userList.size() > 0) {
            return userList.get(0);
        }
        return null;
    }

    private List<Resource> getUserResource(Integer userId) {
        //根据userid获取roleid 再根据roleid获取对应的resource
        return userResourceDao.fetchResourceByUserId(userId);
    }


}
