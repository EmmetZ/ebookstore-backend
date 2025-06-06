package com.sjtu.se2321.backend;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class Utils {

    public static ResponseEntity<Resource> getImageResource(String dir, String fileName) throws IOException {
        Path filePath = Paths.get(dir).resolve(fileName);

        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            String contentType = Files.probeContentType(filePath);
            return ResponseEntity.ok()
                    .header("Content-Type", contentType)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public static Long getUserId(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        return userId;
    }

}
