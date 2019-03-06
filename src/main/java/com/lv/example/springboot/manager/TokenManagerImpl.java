package com.lv.example.springboot.manager;

import com.lv.example.springboot.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class TokenManagerImpl implements ITokenManager{

    @Override
    public UserEntity getUserInfoByToken(String token) {

        if(StringUtils.isEmpty(token)){
            return null;
        }

        if("111".equals(token)){
            UserEntity entity = new UserEntity();
            entity.setId(1);
            entity.setUsername("ming");
            return entity;
        }

        return null;

    }
}
