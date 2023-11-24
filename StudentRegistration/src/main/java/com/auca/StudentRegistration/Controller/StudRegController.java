package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.Student;
import com.auca.StudentRegistration.Model.StudentRegistration;
import com.auca.StudentRegistration.Service.StudRegistrationService;
import com.auca.StudentRegistration.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/studentRegistration" , produces = (MediaType.APPLICATION_JSON_VALUE), consumes = (MediaType.APPLICATION_JSON_VALUE))
public class StudRegController {
    private static final long serialVersionUID = 1L;
    @Autowired
    private StudRegistrationService regService;
    //creating
    @PostMapping(value = "/saveRegistration")
    public ResponseEntity<?> createReg(@RequestBody StudentRegistration studentReg){
        if(studentReg != null ){
            String message = regService.saveRegistration(studentReg);
            if(message != null){
                return new ResponseEntity<>("Student Registered Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Student Not Registered",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    //list
    @GetMapping(value = "/listRegistrations")
    public ResponseEntity<List<StudentRegistration>> listRegs() {
        List<StudentRegistration> studentReg = regService.listStudentsReg();
        return new ResponseEntity<>(studentReg, HttpStatus.OK);
    }
    //update
    @PutMapping(value = "/updateRegistration/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentRegistration regStudent) {
        if (regStudent != null) {
            String message = regService.updateStudentReg(id, regStudent);
            if (message != null) {
                return new ResponseEntity<>("Student Registration Updated Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Registration Not Updated Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
    //delete
    @DeleteMapping(value = "/deleteRegistration/{id}")
    public ResponseEntity<String> deleteStudReg(@PathVariable Integer id) {
        if (id != null) {
            String message = regService.deleteStudentReg(id);
            if (message != null) {
                return new ResponseEntity<>("Student Registration Deleted Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Student Registration Not Deleted Successfully", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
