package com.sjtu.se2321.backend.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sjtu.se2321.backend.Utils;
import com.sjtu.se2321.backend.dto.AddrReqBody;
import com.sjtu.se2321.backend.dto.AddressDTO;
import com.sjtu.se2321.backend.dto.Result;
import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.entity.Image;
import com.sjtu.se2321.backend.service.ImageService;
import com.sjtu.se2321.backend.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @Value("${file.upload-avatar-dir}")
    private String uploadDir;

    @GetMapping("/api/user/me")
    public ResponseEntity<UserDTO> getMe(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        UserDTO user = userService.getMe(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @GetMapping("/api/user/me/addresses")
    public ResponseEntity<List<AddressDTO>> getUserAddress(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");
        List<AddressDTO> addresses = userService.findAllAddressByUserId(userId);
        return ResponseEntity.ok(addresses);
    }

    @PostMapping("/api/user/me/avatar")
    public ResponseEntity<Result<Void>> uploadAvatar(HttpServletRequest request,
            @RequestParam MultipartFile file) throws IOException {
        HttpSession session = request.getSession(false);
        Long userId = (Long) session.getAttribute("userId");

        Path uploadPath = Paths.get(uploadDir);

        String fileName = UUID.randomUUID().toString() + "."
                + FilenameUtils.getExtension(file.getOriginalFilename());
        Path filePath = uploadPath.resolve(fileName);
        String preAvatar;

        try {
            // if upload dir does not exists, then create it;
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            // 保存文件
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // save to Image table
            Image avatar = userService.findUserById(userId).getAvatar();
            preAvatar = avatar.getFileName();
            avatar.setFileName(fileName);
            imageService.save(avatar);

            // delete previous avatar image file
            if (preAvatar != null) {
                Path prePath = uploadPath.resolve(preAvatar);
                Files.deleteIfExists(prePath);
            }
            return ResponseEntity.ok(Result.success("上传成功")); // 返回图片URL给前端
        } catch (Exception e) {
            // if file is saved, delete it
            Files.deleteIfExists(filePath);
            throw e;
        }
    }

    @GetMapping("/api/user/avatars/{fileName}")
    public ResponseEntity<Resource> getAvatar(@PathVariable String fileName) throws IOException {
        return Utils.getImageResource(uploadDir, fileName);
    }

    @PostMapping("/api/user/me/addresses")
    public ResponseEntity<Result<Void>> addAddress(
            HttpServletRequest request,
            @RequestBody AddrReqBody body) {
        Long userId = Utils.getUserId(request);
        userService.saveAddress(userId, body);
        return ResponseEntity.ok(Result.success("添加地址成功"));
    }

}
