package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/load")
public class LoadLibraryController {

    @PostMapping("/{path}")
    public String loadLibrary(@PathVariable String path) {
        try {
            System.out.println("Loading library from path: " + path);
            Runtime.getRuntime().load(path);
            return "Library loaded successfully!";
        } catch (Exception e) {
            return "Failed to load library: " + e.getMessage();
        }
    }
}
bash
curl -X POST http://localhost:8080/load/libs/example.dll