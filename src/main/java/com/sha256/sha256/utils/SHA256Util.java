package com.sha256.sha256.utils;

import com.sha256.sha256.bean.Block;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Util {
    //Applies SHA256 to a string and returns the result
    //SHA256 encryption
    public static String applySha256(String input){
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //Applies sha256 to our input
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            //This will contain hash as hexidecimal
            StringBuffer hexString = new StringBuffer();
            for(int i=0;i<hash.length;i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length()==1){
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    //calculate the hash use previoushash , timestamp , data
    public static String calculateHash(Block block){
        String calculateHash = SHA256Util.applySha256(block.getPreviousHash() + Long.toString(block.getTimeStamp()) + block.getData());
        return calculateHash;
    }

}
