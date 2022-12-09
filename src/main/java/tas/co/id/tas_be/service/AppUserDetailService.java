
package tas.co.id.tas_be.service;

import lombok.AllArgsConstructor;
import tas.co.id.tas_be.model.AppUserDetail;
import tas.co.id.tas_be.model.User;
import tas.co.id.tas_be.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AppUserDetailService implements UserDetailsService{
    
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("Username or password is not correct"));
        return new AppUserDetail(user);
    }
    
}
