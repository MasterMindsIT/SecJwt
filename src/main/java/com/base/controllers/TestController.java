package com.base.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/method")
@PreAuthorize("denyAll()")
public class TestController {

    @GetMapping("/get")
    @PreAuthorize("permitAll()")
    public String callGet(){
        return "Method Called With GET";
    }

    @PostMapping("/post")
    @PreAuthorize("hasAuthority('READ') or hasAuthority('CREATE')")
    public String callPost(){
        return "Method Called With POST";
    }

    @PutMapping("/put")
    @PreAuthorize("hasAuthority('READ')")
    public String callPut(){
        return "Method Called With PUT";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('REFACTOR')")
    public String callDelete(){
        return "Method Called With DELETE";
    }
}