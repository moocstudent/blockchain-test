package com.sha256.sha256.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.GsonBuilder;
import com.sha256.sha256.bean.Block;
import com.sha256.sha256.utils.SHA256Util;

import java.util.ArrayList;

public class TestSHA256 {

    //声明一个区块链,用于添加Block实体
    public static ArrayList<Block> blockChain = new ArrayList<>();

    public static void main(String[] args) {
        //test1 测试三个被加密字符串 加密后的hash值的差别
        /**
         * 虽然第三条信息仅仅多一个".",但加密后的数据hash相差极大
         */
        String message0 = "我是要被加密的信息";
        String message1 = "我是要被加密的信息";
        String message2 = "我是要被加密的信息.";
        String encryptionMessage0 = SHA256Util.applySha256(message0);
        String encryptionMessage1 = SHA256Util.applySha256(message1);
        String encryptionMessage2 = SHA256Util.applySha256(message2);
        System.out.println(encryptionMessage0);
        System.out.println(encryptionMessage1);
        System.out.println(encryptionMessage2);

        //test2 创建区块链逻辑, 因为第一个块没有上一个块的hash头部值,所以输入0 作为前一个块的previous hash
        /**
         * 由于在{@link SHA256Util#calculateHash(Block)}
         * 中对同时产生的new Date().getTime() (timestamp)
         * 也加入进行了hash加密,所以固有的message (data)及
         * previoushash之和进行了加密.
         */
        Block genesisBlock = new Block("这是第一个区块中的要被加密的信息和交易信息","0");
        String hash1 = genesisBlock.getHash();
        System.out.println("Hash for block 1 : "+hash1);

        Block secondBlock = new Block("这是第二个区块,以及其中信息!!!它的前区块头部hash我们拿上一个的来使用",hash1);
        String hash2 = secondBlock.getHash(); //
        System.out.println("Hash for block 2 : "+hash2);

        Block thirdBlock = new Block("这是第三个区块,它的hash应该已经被前两个的信息纳入进来了,它的hash如果对不上,那么说明前面的信息被改动过了",hash2);
        String hash3 = thirdBlock.getHash();
        System.out.println("Hash for block 3 : "+hash3);

        //test3 add our blocks to the blockchain ArrayList :
        blockChain.add(new Block("区块链上第一小节","0"));
        blockChain.add(new Block("区块链第二小节",blockChain.get(blockChain.size()-1).getHash()));
        blockChain.add(new Block("区块链第三小节",blockChain.get(blockChain.size()-1).getHash()));

//        JSONArray blockChainJson1 = (JSONArray)JSONArray.toJSON(blockChain); //JSONArray是不排版的
//        System.out.println(blockChainJson1);
        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);

        System.out.println(blockChainJson);
    }
}
