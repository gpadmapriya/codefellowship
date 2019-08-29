package com.padmapg.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;
    String username;
    String password;
    String firstName;
    String lastName;
    String bio;
    Date dateOfBirth;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    List<Post> posts;

    @ManyToMany
    @JoinTable(
            name="follow_users",
            joinColumns = { @JoinColumn(name="primaryUser")},
            inverseJoinColumns = {@JoinColumn(name="followedUsers")}
    )
    Set<ApplicationUser> usersThatIFollow;

    @ManyToMany(mappedBy = "usersThatIFollow")
    Set<ApplicationUser> usersThatFollowMe;

    public ApplicationUser(){}
    public ApplicationUser(String username, String password, String firstName, String lastName, String bio, Date dateOfBirth){
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void addFollowing(ApplicationUser followedUser){
        usersThatIFollow.add(followedUser);
    }

    public Set<ApplicationUser> getUsersThatIFollow() {
        return usersThatIFollow;
    }

    public Set<ApplicationUser> getUsersThatFollowMe() {
        return usersThatFollowMe;
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (this.posts.size() > 0){
            for (Post post : this.posts){
                sb.append(post.body);
                sb.append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
        }
        return sb.toString();
    }
}
