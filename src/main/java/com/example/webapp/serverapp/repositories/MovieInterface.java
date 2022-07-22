package com.example.webapp.serverapp.repositories;

public interface MovieInterface {
    Long getMovie_id();
    String getMovie_name();
    byte[] getMovie_image();
    byte[] getMovie_title();
    byte[] getMovie_focus();
}
