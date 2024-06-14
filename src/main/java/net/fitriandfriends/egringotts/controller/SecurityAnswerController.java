package net.fitriandfriends.egringotts.controller;

import net.fitriandfriends.egringotts.dto.SecurityAnswerDTO;
import net.fitriandfriends.egringotts.service.SecurityAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/securityanswers")
public class SecurityAnswerController {

    @Autowired
    private SecurityAnswerService securityAnswerService;

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateAnswer(@RequestBody SecurityAnswerDTO securityAnswerDTO) {


        return ResponseEntity.ok(securityAnswerService.validateSecurityAnswer(securityAnswerDTO));

    }

}