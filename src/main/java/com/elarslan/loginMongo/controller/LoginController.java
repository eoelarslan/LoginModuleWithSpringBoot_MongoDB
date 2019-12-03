package com.elarslan.loginMongo.controller;


import com.elarslan.loginMongo.controller.dto.base.GenericResponseDTO;
import com.elarslan.loginMongo.controller.dto.requestdto.RegisterRequestDTO;
import com.elarslan.loginMongo.controller.dto.requestdto.SignInRequestDTO;
import com.elarslan.loginMongo.controller.dto.requestdto.SignOutRequestDTO;
import com.elarslan.loginMongo.controller.dto.responsedto.LoginResponseDTO;
import com.elarslan.loginMongo.model.EmbeddedDocument.User;
import com.elarslan.loginMongo.repository.UserRepository;
import com.elarslan.loginMongo.service.LoginService;
import com.elarslan.loginMongo.service.MailService;
import com.elarslan.loginMongo.util.enums.MessageStatus;
import com.elarslan.loginMongo.util.helper.Base64Helper;
import com.elarslan.loginMongo.util.helper.MessageHelper;
import javassist.tools.web.BadHttpRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.elarslan.loginMongo.util.enums.MessageStatus.*;


@RestController
@RequestMapping(value = "/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MessageHelper messageHelper;

    @Autowired
    private MailService mailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    // TODO SESSION WILL BE HANDLED


    @PostMapping("/register")
    public ResponseEntity createUser(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) throws BadHttpRequest {

        String emailToken = mailService.createEmailToken();

        MessageStatus messageStatus = loginService.saveUserAsPassive(registerRequestDTO, emailToken);
        switch (messageStatus) {
            case AUTH_FAILED:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseDTO<>(HttpStatus.FORBIDDEN,
                        messageHelper.getMessageByMessageStatus(AUTH_FAILED, null), null));
            case PASSWORD_DOES_NOT_MATCH:
                return ResponseEntity.badRequest().body(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST,
                        messageHelper.getMessageByMessageStatus(PASSWORD_DOES_NOT_MATCH, null), null));
            case DUBLICATE_ENTRY:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new GenericResponseDTO<>(HttpStatus.FORBIDDEN,
                        messageHelper.getMessageByMessageStatus(DUBLICATE_ENTRY, null), null));
            case USER_CREATED_AS_PASSIVE:

                String subject = messageHelper.getMessage("login.register.mail.subject", null);
                String message = mailService.createRegisterMessage("login.register.mail.message",
                        registerRequestDTO.getEmail(), emailToken);

                Optional<User> user = userRepository.findByEmail(registerRequestDTO.getEmail());
                user.get().setMailSentTime(LocalDateTime.now());
                userRepository.save(user.get());

                mailService.sendMail(registerRequestDTO.getEmail(), subject, message);

                return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                        messageHelper.getMessageByMessageStatus(USER_CREATED_AS_PASSIVE, null), user.get()));

            default:
                return ResponseEntity.badRequest().body(new GenericResponseDTO<>(HttpStatus.FORBIDDEN,
                        messageHelper.getMessageByMessageStatus(MessageStatus.BAD_CREDENTIALS, null), null));
        }
    }

    @GetMapping(value = "/user/mail/verification/{mail}/{token}")
    public ResponseEntity mailVerification(@PathVariable("mail") String mailAddress,
                                           @PathVariable("token") String mailToken) {

        MessageStatus messageStatus = loginService.saveUserAsActive(Base64Helper.decoder(mailAddress), mailToken);
        switch (messageStatus) {
            case USER_CREATED:
                return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                        messageHelper.getMessageByMessageStatus(MessageStatus.USER_CREATED, null), null));
            default:
                return ResponseEntity.badRequest().body(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST,
                        messageHelper.getMessageByMessageStatus(MessageStatus.BAD_CREDENTIALS, null), null));
        }
    }

    @PostMapping(value = "/user/signin")
    public ResponseEntity signIn(@RequestBody SignInRequestDTO signInRequestDTO) {

/*        List<LoginResponseDTO> userLoginResponse = new ArrayList<>();*/
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        MessageStatus messageStatus = loginService.userSignIn(signInRequestDTO);
        Optional<User> user;

        switch (messageStatus) {
            case OK:
                user = userRepository.findByEmail(signInRequestDTO.getEmail());
                modelMapper.map(user.get(), loginResponseDTO);

                return ResponseEntity.ok().body(new GenericResponseDTO<>(HttpStatus.ACCEPTED,
                        messageHelper.getMessageByMessageStatus(MessageStatus.OK, null), loginResponseDTO));
            default:
                return ResponseEntity.badRequest().body(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST,
                        messageHelper.getMessageByMessageStatus(AUTH_FAILED, null), null));
        }
    }

    @DeleteMapping("/user/logout")
    public ResponseEntity<?> deleteUser(@RequestBody SignOutRequestDTO signOutRequestDTO) {

        Optional<User> user = userRepository.findByEmail(signOutRequestDTO.getEmail());
        // TODO SESSION WILL BE HANDLED
        if (user.isPresent()) {
            return ResponseEntity.ok(new GenericResponseDTO<>(HttpStatus.OK, "User Logged Out", null));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new GenericResponseDTO<>(HttpStatus.BAD_REQUEST, "User Not Found", null));
        }
    }
}