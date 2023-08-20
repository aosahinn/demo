package com.preschool.demo.service.user;

import com.preschool.demo.controller.mapper.UserMapper;
import com.preschool.demo.data.entity.user.User;
import com.preschool.demo.data.repository.BaseRepository;
import com.preschool.demo.data.repository.user.UserRepository;
import com.preschool.demo.service.AbstractEntityService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Slf4j
@Service
public class UserService extends AbstractEntityService<User, String> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public BaseRepository<User, String> getRepository() {
        return userRepository;
    }

    @Override
    protected User verifySave(User entity) {
        return super.verifySave(entity);
    }

    @Override
    protected User verifyPut(User theReal, User forSave) {
        return super.verifyPut(theReal, forSave);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
