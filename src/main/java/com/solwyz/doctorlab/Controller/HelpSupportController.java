package com.solwyz.doctorlab.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.solwyz.doctorlab.Entity.HelpSupport;
import com.solwyz.doctorlab.Service.HelpSupportService;
import com.solwyz.doctorlab.pojo.response.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/helpSupport")
@Tag(name = "Help ", description = "APIs for help related operations")
public class HelpSupportController {
	
	@Autowired
    private HelpSupportService helpSupportService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitHelpQuery(@RequestBody HelpSupport helpSupport) {
        return ResponseEntity.ok(helpSupportService.submitQuery(helpSupport));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<HelpSupport>>> getAllHelpQueries() {
        List<HelpSupport> helpSupportList = helpSupportService.getAllQueries();
        ApiResponse<List<HelpSupport>> response = new ApiResponse<>("success", helpSupportList);
        return ResponseEntity.ok(response);
    }

}
