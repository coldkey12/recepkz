package kz.don.recepkz.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kz.don.recepkz.model.User;
import kz.don.recepkz.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void register(User user){
        userRepository.save(user);
    }

    public boolean login(User user, HttpServletRequest req){
        User userCheck = userRepository.findByUsername(user.getUsername());
        if(userCheck!=null && userCheck.getPassword().equals(user.getPassword())){
            HttpSession session = req.getSession();
            session.setAttribute("currentUser", userCheck);
            return true;
        }
        return false;
    }

    public boolean isUserExistByUsername(String username){
        if(userRepository.findByUsername(username)==null){
            return false;
        }
        return true;
    }

    public List<User> listAllUsers() {
        return userRepository.findAll();
    }
}
