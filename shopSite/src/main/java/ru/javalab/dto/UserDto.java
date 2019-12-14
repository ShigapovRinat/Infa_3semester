package ru.javalab.dto;

import ru.javalab.models.User;

public class UserDto implements Dto {
    private Integer id;
    private String login;
    private Integer role;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserDto(){

    }

    private UserDto(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.role = builder.role;
        this.token = builder.token;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Integer id;
        private String login;
        private Integer role;
        private String token;


        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder login(String login){
            this.login = login;
            return this;
        }

        public Builder token(String token){
            this.token = token;
            return this;
        }

        public Builder role(Integer role){
            this.role = role;
            return this;
        }

        public UserDto build(){
            return new UserDto(this);
        }

    }

    public static UserDto from(User user) {
        return UserDto.builder().id(user.getId()).login(user.getLogin()).role(user.getRole()).build();
    }

}
