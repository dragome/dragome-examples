package com.dragome.example;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class ClientApplication {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new AppInjector());

        MyApplication app = injector.getInstance(MyApplication.class);

        app.sendMessage("Hi Pankaj", "pankaj@abc.com");
    }

}