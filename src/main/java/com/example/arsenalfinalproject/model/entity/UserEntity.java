package com.example.arsenalfinalproject.model.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity{

        private String username;
        private String firstName;
        private String lastName;
        private LocalDate dateBirth;
        private String email;
        private String password;
        private Set<RoleEntity> roles = new HashSet<>();
        private String interest;
        private String favoritePlayer;
        private String loveTrip;
        private String description;


        public UserEntity() {
        }

        @Column(name = "username" , nullable = false , unique = true)
        @Size(min = 3 , max=20)
        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        @Column(name = "first_name" , nullable = false)
        @Size(min = 2 , max=20)
        public String getFirstName() {
                return firstName;
        }

        public void setFirstName(String firstName) {
                this.firstName = firstName;
        }

        @Column(name = "last_name" , nullable = false)
        @Size(min = 2 , max = 20)
        public String getLastName() {
                return lastName;
        }

        public void setLastName(String lastName) {
                this.lastName = lastName;
        }

        @Column(name = "date_birth" , nullable = false)
        public LocalDate getDateBirth() {
                return dateBirth;
        }

        public void setDateBirth(LocalDate dateBirth) {
                this.dateBirth = dateBirth;
        }

        @Column(name = "email" , nullable = false)
        @Size(min=5 , max = 30)
        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Column(name = "password" , nullable = false)
        @Size(min = 3)
        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        @ManyToMany(fetch = FetchType.EAGER)
        public Set<RoleEntity> getRoles() {
                return roles;
        }

        public void setRoles(Set<RoleEntity> roles) {
                this.roles = roles;
        }

        @Column
        public String getInterest() {
                return interest;
        }

        public UserEntity setInterest(String interest) {
                this.interest = interest;
                return this;
        }
        @Column
        public String getFavoritePlayer() {
                return favoritePlayer;
        }

        public UserEntity setFavoritePlayer(String favoritePlayer) {
                this.favoritePlayer = favoritePlayer;
                return this;
        }

        @Column
        @Lob
        public String getLoveTrip() {
                return loveTrip;
        }

        public UserEntity setLoveTrip(String loveTrip) {
                this.loveTrip = loveTrip;
                return this;
        }

        @Column
        @Lob
        public String getDescription() {
                return description;
        }

        public UserEntity setDescription(String description) {
                this.description = description;
                return this;
        }
}
