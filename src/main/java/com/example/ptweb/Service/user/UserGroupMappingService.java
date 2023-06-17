package com.example.ptweb.Service.user;

import com.example.ptweb.repository.user.UserGroupMappingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupMappingService {
    private final UserGroupMappingRepository userGroupMappingRepository;

    public UserGroupMappingService(UserGroupMappingRepository userGroupMappingRepository){
        this.userGroupMappingRepository = userGroupMappingRepository;
    }

    public List<String> getAllUserGroupIds(){
        // 유저 그룹 아이디를 중복없이 아이디를 역순으로 조회
        return userGroupMappingRepository.findDistinctUserGroupId();

    }
}
