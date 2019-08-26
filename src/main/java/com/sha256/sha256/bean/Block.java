package com.sha256.sha256.bean;

import com.sha256.sha256.utils.SHA256Util;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {

    private long index;
    private String hash; // our signature
    private String previousHash; // the hash of previous block
    private String data; //our data will be a simple message.
    private long timeStamp; //as number of milliseconds since 1/1/1970.

    //Block Constructor
    public Block(long index,String data,String previousHash){
        this.index = index;
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = SHA256Util.calculateHash(this); //Making sure we do this after we set the other values.
    }
}
