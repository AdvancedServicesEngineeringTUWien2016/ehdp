package at.ac.tuwien.auth;

import at.ac.tuwien.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * reference: http://ingini.org/2015/03/26/authentication-authorization-schema-design-with-mongodb/
 */
@Service
public class MongoDbAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private UserRepository users;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
          UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username,
           UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser;

        try {
            at.ac.tuwien.domain.User u = users.getByEmail(username);
            List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
            for(String s : u.getRoles()) {
                gas.add(new SimpleGrantedAuthority(s));
            }
            loadedUser = new User(u.getEmail(), u.getPassword(), gas);
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException(
                    "UserDetailsService returned null, which is an interface contract violation");
        }
        return loadedUser;
    }
}