package com.sjtu.se2321.backend.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sjtu.se2321.backend.dao.AddressDAO;
import com.sjtu.se2321.backend.dao.UserDAO;
import com.sjtu.se2321.backend.dto.AddrReqBody;
import com.sjtu.se2321.backend.dto.AddressDTO;
import com.sjtu.se2321.backend.dto.AdminUserDTO;
import com.sjtu.se2321.backend.dto.OtherUserDTO;
import com.sjtu.se2321.backend.dto.PageResult;
import com.sjtu.se2321.backend.dto.UserDTO;
import com.sjtu.se2321.backend.entity.Address;
import com.sjtu.se2321.backend.entity.User;
import com.sjtu.se2321.backend.entity.User.Role;
import com.sjtu.se2321.backend.entity.UserAuth;
import com.sjtu.se2321.backend.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    public User findUserById(Long userId) {
        return userDAO.findById(userId);
    }

    @Override
    public UserDTO getMe(Long userId) {
        if (userId != null) {
            User user = userDAO.findById(userId);
            if (user != null) {
                return UserDTO.fromUser(user);
            }
        }
        return null;
    }

    public List<AddressDTO> findAllAddressByUserId(Long userId) {
        List<Address> addressList = userDAO.findById(userId).getAddresses();
        List<AddressDTO> result = new ArrayList<>();
        for (Address addr : addressList) {
            result.add(new AddressDTO(addr));
        }
        return result;
    }

    @Override
    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    @Transactional
    public void saveAddress(Long userId, AddrReqBody body) {
        Address addr = new Address();
        addr.setReceiver(body.getReceiver());
        addr.setTel(body.getTel());
        addr.setAddress(body.getAddress());
        addr.setUser(userDAO.getReferenceById(userId));
        addressDAO.save(addr);
    }

    @Override
    public OtherUserDTO findOtherUserById(Long userId) {
        User user = userDAO.findById(userId);
        OtherUserDTO dto = new OtherUserDTO(user);
        return dto;
    }

    @Override
    @Transactional
    public void deleteAddressById(Long id) {
        addressDAO.deleteById(id);
    }

    @Override
    @Transactional
    public void changePassword(Long id, String password) {
        User user = userDAO.findById(id);
        UserAuth userAuth = user.getUserAuth();
        userAuth.setPassword(passwordEncoder.encode(password));
        user.setUserAuth(userAuth);
        userDAO.save(user);
    }

    @Override
    @Transactional
    public void changeIntro(Long id, String intro) {
        User user = userDAO.findById(id);
        user.setIntroduction(intro);
        userDAO.save(user);
    }

    @Override
    public PageResult<AdminUserDTO> findByRole(Integer pageIndex, Integer pageSize, Role role) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<User> users = userDAO.findByRole(role, pageable);
        List<AdminUserDTO> dtos = new ArrayList<>();
        for (User user : users) {
            AdminUserDTO dto = new AdminUserDTO(user);
            dtos.add(dto);
        }
        return new PageResult<>(users.getTotalPages(), dtos);
    }

    @Override
    @Transactional
    public void changeUserStatus(Long id, Boolean status) {
        User user = userDAO.findById(id);
        user.setIsBanned(status);
        userDAO.save(user);
    }

}