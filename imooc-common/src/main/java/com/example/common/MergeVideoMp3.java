package com.example.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideoMp3 {

    private String ffmpegEXE;

    public MergeVideoMp3(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath, String mp3InputPath,
                          double seconds, String videoOutputPath) throws Exception {
//		ffmpeg.exe -i 苏州大裤衩.mp4 -i bgm.mp3 -t 7 -y 新的视频.mp4
        List<String> command = new ArrayList<>();
        command.add(ffmpegEXE);

        command.add("-i");
        command.add(videoInputPath);

        command.add("-i");
        command.add(mp3InputPath);

        command.add("-t");
        command.add(String.valueOf(seconds));

//        command.add("-filter_complex amix=inputs=2");
        command.add("-c:v copy -c:a aac -strict experimental");
        command.add("-map 0:v:0 -map 1:a:0");



        command.add("-y");
        command.add(videoOutputPath);

		for (String c : command) {
			System.out.print(c + " ");
		}

        ProcessBuilder builder = new ProcessBuilder(command);
        Process process = builder.start();

        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line = "";
        while ( (line = br.readLine()) != null ) {
        }

        if (br != null) {
            br.close();
        }
        if (inputStreamReader != null) {
            inputStreamReader.close();
        }
        if (errorStream != null) {
            errorStream.close();
        }

    }

    public static void main(String[] args) {
        MergeVideoMp3 ffmpeg = new MergeVideoMp3("C:\\ffmpeg\\bin\\ffmpeg.exe");
        try {
            ffmpeg.convertor("C:\\input.mp4", "C:\\test.mp3", 10, "C:\\这是通过java生产的视频.mp4");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
//ffmpeg.exe -i test.mp3 -i input.mp4 -t 10 -filter_complex amix=inputs=2 output.mp4 音视频合并（保留视频原音）
//FFMPEG_PATH + " -i " + videoInputPath + " -i " + audioInputPath
//        + " -c:v copy -c:a aac -strict experimental " +
//        " -map 0:v:0 -map 1:a:0 "
//        + " -y " + videoOutPath;