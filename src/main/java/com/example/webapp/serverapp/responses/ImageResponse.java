package com.example.webapp.serverapp.responses;

import lombok.Data;

@Data
public class ImageResponse {
    public ImageResponse(String movie_id, String movie_image){
        this.movie_id = movie_id;
        this.movie_image = movie_image;
    }
    private String movie_id;
    private String movie_image;
}
