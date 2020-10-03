package Model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String name;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "follower_followed",
            joinColumns = {@JoinColumn(name = "follower_fk")},
            inverseJoinColumns = {@JoinColumn(name = "followed_fk")})
    private Set<AppUser> following = new HashSet<>();
    @ManyToMany(mappedBy = "following", cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    private Set<AppUser> follower = new HashSet<>();
    @CreationTimestamp
    private Date registeredSince;
    private String password;


    public AppUser() {

    }

    public AppUser(Long id, String login, String name, String lastName, String email, Set following, Set follower, Date registeredSince) {

        this.id = id;
        this.login = login;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.following = following;
        this.follower = follower;
        this.registeredSince = registeredSince;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lestName) {
        this.lastName = lestName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<AppUser> getFollowing() {
        return following;
    }

    public void setFollowing(Set<AppUser> following) {
        this.following = following;
    }

    public Set<AppUser> getFollower() {
        return follower;
    }

    public void setFollower(Set<AppUser> followeb) {
        this.follower = followeb;
    }

    public Date getRegisteredSince() {
        return registeredSince;
    }

    public void setRegisteredSince(Date registeredSince) {
        this.registeredSince = registeredSince;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return id.equals(appUser.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    public static class UserBuilder {
        private String name;
        private String lastName;
        private String login;
        private String password;
        private String email;
        private Date registeredSince;
        public static UserBuilder getBuilder() {
            return new UserBuilder();
        }
        public UserBuilder login(String login) {
            this.login = login;
            return this;
        }
        public UserBuilder name(String name) {
            this.name = name;
            return this;
        }
        public UserBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        }
        public UserBuilder email(String email) {
            this.email = email;
            return this;
        }
        public UserBuilder registeredSince(Date dateOfRegistration) {
            this.registeredSince = dateOfRegistration;
            return this;
        }
        public AppUser build() {
            AppUser user = new AppUser();
            user.setLogin(this.login);
            user.setPassword(this.password);
            user.setName(this.name);
            user.setLastName(this.lastName);
            user.setEmail(this.email);
            user.setRegisteredSince(this.registeredSince);
            return user;
        }
    }


}
