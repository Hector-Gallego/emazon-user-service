package com.emazon.emazonuserservice.domain.model;

import java.time.LocalDate;


public class User {

    private final Long id;
    private final String name;
    private final String lastName;
    private final String identityDocument;
    private final String phoneNumber;
    private final LocalDate birthDate;
    private final String email;
    private final String password;
    private final Role role;


    public Long getId() {return id;}
    public String getName() {return name;}
    public String getLastName() {return lastName;}
    public String getIdentityDocument() {return identityDocument;}
    public String getPhoneNumber() {return phoneNumber;}
    public LocalDate getBirthDate() {return birthDate;}
    public String getEmail() {return email;}
    public String getPassword() {return password;}
    public Role getRole() {return role;}

    protected User(User.Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.identityDocument = builder.identityDocument;
        this.phoneNumber = builder.phoneNumber;
        this.birthDate = builder.birthDate;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;

    }

    public static User.Builder builder() {
        return new User.Builder();
    }


    public static class Builder {
        private Long id;
        private String name;
        private String lastName;
        private String identityDocument;
        private String phoneNumber;
        private LocalDate birthDate;
        private String email;
        private String password;
        private Role role;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder identityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
            return this;
        }

        public Builder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder role(Role role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }

}
