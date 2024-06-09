package net.fitriandfriends.egringotts;

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
