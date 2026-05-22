/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messagechat;

import java.util.UUID;

/**
 *
 * @author Student
 */
 import java.util.UUID;
public class Message {
    

    
    private final int messageNumber;
    private final String messageId;
    private final String recCell; 
    private final String txt;     
    
    public Message(int messageNumber, String recCell, String txt) {
        this.messageNumber = messageNumber;
        this.recCell = recCell;
        this.txt = txt;
        this.messageId = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 10);
    }
    
    public boolean checkMessageId() {
        return messageId != null && messageId.length() == 10;
    }
    
    public String createMessageHash() {
        if (txt == null || txt.trim().isEmpty()) {
            return "MSG-" + messageNumber + "-EMPTY";
        }
        
        // Uses alternative localized standard utility operations to extract boundary strings
        String content = txt.trim();
        int spaceIndex = content.indexOf(" ");
        String first = (spaceIndex == -1) ? content : content.substring(0, spaceIndex);
        int lastSpaceIndex = content.lastIndexOf(" ");
        String last = (lastSpaceIndex == -1) ? content : content.substring(lastSpaceIndex + 1);
        
        return "HASH:" + messageNumber + ":" + first.toUpperCase() + ":" + last.toUpperCase();
    }
    
       public String storeMessage() {
        return String.format(
            "{\n  \"id\": \"%s\",\n  \"cell\": \"%s\",\n  \"body\": \"%s\",\n  \"checksum\": \"%s\"\n}",
            messageId, recCell, txt, createMessageHash()
        );
    }
    
 
    
    public int getMessageNumber() { return messageNumber; }
    public String getMessageId() { return messageId; }
    public String getRecCell() { return recCell; }
    public String getTxt() { return txt; }
}
   

