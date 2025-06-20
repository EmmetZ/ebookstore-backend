package com.sjtu.se2321.backend.service;

import java.util.List;

import com.sjtu.se2321.backend.dto.AddrReqBody;
import com.sjtu.se2321.backend.dto.AddressDTO;
import com.sjtu.se2321.backend.dto.AdminUserDTO;
import com.sjtu.se2321.backend.dto.OtherUserDTO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.entity.User;

public interface UserService {

    public User findUserByUsername(String username);

    /**
     * 通过ID获取用户信息
     */
    public User findUserById(Long userId);

    // 获取当前登录的信息
    public UserDTO getMe(Long userId);

    // 获取用户地址信息
    public List<AddressDTO> findAllAddressByUserId(Long userId);

    public void save(User user);

    public User findByEmail(String email);

    public void saveAddress(Long userId, AddrReqBody body);

    public OtherUserDTO findOtherUserById(Long userId);

    public void deleteAddressById(Long id);

    public void changePassword(Long id, String password);

    public void changeIntro(Long id, String intro);

    public PageResult<AdminUserDTO> findByRole(Integer pageIndex, Integer pageSize, User.Role role);

    public void changeUserStatus(Long id, Boolean status);

}