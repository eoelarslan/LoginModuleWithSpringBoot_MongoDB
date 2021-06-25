package com.elarslan.loginMongo.controller;


import com.elarslan.loginMongo.controller.dto.base.GenericResponseDTO;
import com.elarslan.loginMongo.controller.dto.requestdto.RegisterRequestDTO;
import com.elarslan.loginMongo.exception.ResourceNotFoundException;
import com.elarslan.loginMongo.model.EmbeddedDocument.User;
import com.elarslan.loginMongo.repository.UserRepository;
import com.elarslan.loginMongo.util.helper.MessageHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.elarslan.loginMongo.util.enums.MessageStatus.DATA_RETRIEVED;
import static com.elarslan.loginMongo.util.enums.MessageStatus.DELETED;
import static com.elarslan.loginMongo.util.enums.MessageStatus.USER_UPDATED;


@RestController
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;
        
    @Autowired
    private MessageHelper messageHelper;

   //...
}
