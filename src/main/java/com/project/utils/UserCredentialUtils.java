package com.project.utils;

import com.project.model.User;

/**
 * Created by BABAWANDE on 12/24/2016.
 */
public class UserCredentialUtils {

    public String getHashedPasswordForUser(User user){
        byte [] userEmailInBytes = user.getEmailAddress().getBytes();
        byte [] userPasswordInBytes = user.getPassword().getBytes();

        byte [] hash = new byte[userEmailInBytes.length + userPasswordInBytes.length];

        for (int i = 0; i < hash.length; i++){
            if (i < userEmailInBytes.length){
                hash[i] = userEmailInBytes[i];
            }
            else{
                hash[i] = userPasswordInBytes[i - userEmailInBytes.length];
            }
        }

        return new String (hash);
    }

}
