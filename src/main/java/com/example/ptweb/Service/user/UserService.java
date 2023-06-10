package com.example.ptweb.Service.user;

import com.example.ptweb.repository.user.UserEntity;
import com.example.ptweb.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public User getUser(final String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserModelMapper.INSTANCE.toUser(userEntity);

    }
}
