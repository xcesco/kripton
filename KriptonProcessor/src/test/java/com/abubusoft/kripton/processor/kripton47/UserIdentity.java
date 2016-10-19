//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.abubusoft.kripton.processor.kripton47;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class UserIdentity {
    private static final long serialVersionUID = -8872816586169055466L;
    private String name;
    private String email;
    private String username;

    public UserIdentity() {
    }

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String toString() {
        return "UserIdentity [name=" + this.name + ", email=" + this.email + ", username=" + this.username + "]";
    }
}
