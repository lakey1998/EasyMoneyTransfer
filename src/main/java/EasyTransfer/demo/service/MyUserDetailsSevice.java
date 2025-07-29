package EasyTransfer.demo.service;

import EasyTransfer.demo.dao.QRUserDao;
import EasyTransfer.demo.model.QRUser;
import EasyTransfer.demo.model.userPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyUserDetailsSevice implements UserDetailsService {

    @Autowired
    private QRUserDao repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //we are serching by its account number because of that username wanna change to account number
        QRUser user = repo.findByUsername(username);

        if(user == null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username Not found");
        }

        return new userPrincipal(user);
    }
}
