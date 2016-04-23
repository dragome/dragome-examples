package com.dragome.example;
public class MockMessageService implements MessageService{

    public boolean sendMessage(String msg, String receipient) {
        return true;
    }

}