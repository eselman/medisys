package com.medisys.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medisys.entities.LoginCredentials;
import com.medisys.entities.User;
import com.medisys.entities.UserStatus;
import com.medisys.repositories.LoginCredentialsRepository;
import com.medisys.repositories.UserRepository;
import com.medisys.util.HexString;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LoginCredentialsRepository loginCredentialsRepository;

	public LoginCredentials getLoginCredentials(User user) {
		if ((user.getUsername() == null) || (user.getPassword() == null))
			return null;

		user = authenticate(user.getUsername(), user.getPassword());
		if (user == null) {
			return null;
		}

		return newLoginCredentials(user);
	}
	
	public LoginCredentials getLoginCredentialByToken(String auth) {

        if (auth == null || !auth.startsWith("MedisysToken")) {
            return null;
        }

        String[] st = auth.split(" ");
        if (st.length != 2) {
            return null;
        }

        String sessionKey = st[1];
        return loginCredentialsRepository.findActiveSession(sessionKey, LocalDateTime.now());
    }
	
	public LoginCredentials logout(String auth) {
        LoginCredentials loginCredentials = this.getLoginCredentialByToken(auth);

        if (loginCredentials != null) {
            loginCredentials.setExpiresDate(LocalDateTime.now());
            loginCredentials = loginCredentialsRepository.save(loginCredentials);
        }

        return loginCredentials;
    }
	
//	public void registerRestLoginAuditing(String username, boolean success, String ipAddress, String userAgent) {
//       User user = userRepository.findByUsernameAndStatus(username, UserStatus.ACTIVE);
//        if (user == null) {
//            return;
//        }
//
//        LoginAudit loginAudit = new LoginAudit(success, ipAddress, userAgent, ApplicationType.REST_API);
//        loginAudit.setClientAccount(clientAccount);
//        loginAuditRepository.save(loginAudit);
//
//        if (success) {
//            clientAccount.clearFailedAttempts();
//        } else {
//            clientAccount.increaseFailedAttempts();
//
//            if (clientAccount.getFailedLoginAttempts() >= 3) {
//                clientAccount.setUserStatus(UserStatus.LOCKED);
//                sendLoginFailureEmail(clientAccount.getFailedLoginAttempts(), ApplicationType.REST_API.getName(),
//                    clientAccount.getEmailAddress());
//            } else {
//                LOG.info("Failed login for client account: " + clientAccount.getEmailAddress());
//            }
//        }
//
//        clientAccountRepository.save(clientAccount);
//    }


	private User authenticate(String username, String hashedPassword) {
		User user = userRepository.findByUsernameAndStatus(username,
				UserStatus.ACTIVE);

//		if (user != null) {
//			if (BCrypt.checkpw(hashedPassword, user.getPassword())) {
//				return user;
//			}
//		}
		return user;
	}

	private LoginCredentials newLoginCredentials(User user) {
		LocalDateTime now = LocalDateTime.now();
		LoginCredentials lc = loginCredentialsRepository.findByUser(user);
		if (lc == null) {
			lc = new LoginCredentials();
		}
		lc.setUser(user);
		lc.setDateCreated(now);
		lc.setSessionKey(createSessionKey());
		lc.setExpiresDate(now.plusHours(8));
		lc = loginCredentialsRepository.save(lc);
		return lc;
	}

	private String createSessionKey() {
		return HexString.getRandomHexString(32);
	}
}