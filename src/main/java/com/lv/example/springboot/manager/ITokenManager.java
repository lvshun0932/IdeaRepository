package com.lv.example.springboot.manager;

import com.lv.example.springboot.entity.UserEntity;

public interface ITokenManager {

    UserEntity getUserInfoByToken(String token);
}
