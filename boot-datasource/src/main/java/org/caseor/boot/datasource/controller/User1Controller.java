package org.caseor.boot.datasource.controller;

import org.caseor.boot.datasource.service.User1Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Fu Kai
 * @since 2021/11/20
 */

@RestController
@RequestMapping("/user1")
public class User1Controller {

    private final User1Service user1Service;

    @Autowired
    public User1Controller(User1Service user1Service){
        this.user1Service = user1Service;
    }


}
