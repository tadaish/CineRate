package com.example.cinerate.utils;

public class YouTubeUtils {

    public static String extractVideoId(String url) {
        String videoId = null;

        // Kiểm tra và xử lý URL chuẩn
        String pattern1 = "https://www.youtube.com/watch?v=";
        String pattern2 = "https://www.youtube.com/watch?v%3D";
        String pattern3 = "https://m.youtube.com/watch?v=";

        if (url.startsWith(pattern1)) {
            videoId = url.substring(pattern1.length());
        } else if (url.startsWith(pattern2)) {
            videoId = url.substring(pattern2.length());
        } else if (url.startsWith(pattern3)) {
            videoId = url.substring(pattern3.length());
        } else if (url.contains("youtu.be/")) {
            // Kiểm tra và xử lý URL ngắn
            String[] parts = url.split("youtu.be/");
            if (parts.length > 1) {
                videoId = parts[1];
            }
        }
        // Loại bỏ các tham số sau ID video
        if (videoId != null) {
            int index = videoId.indexOf('&');
            if (index != -1) {
                videoId = videoId.substring(0, index);
            }
        }
        return videoId;
    }
}

