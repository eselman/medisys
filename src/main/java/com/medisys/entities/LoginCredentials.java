package com.medisys.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "login_credentials")
@NamedQueries (
		@NamedQuery( name="LoginCredentials.findActiveSession", query="SELECT lc FROM LoginCredentials lc WHERE "
				+ " lc.sessionKey=:sessionKey AND lc.expiresDate > :expirationDate")
)
public class LoginCredentials {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Column(name = "session_key", nullable = false)
	private String sessionKey;

	@Column(name = "expires_date", nullable = false)
	private LocalDateTime expiresDate;

	@Column(name = "date_created", nullable = false)
	private LocalDateTime dateCreated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}

	public LocalDateTime getExpiresDate() {
		return expiresDate;
	}

	public void setExpiresDate(LocalDateTime expiresDate) {
		this.expiresDate = expiresDate;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}
}
