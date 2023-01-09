package com.server.diary.common.fileUpload;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.*;

public class FileUpload {
    private String filePath;
    private String fileUrl;
    private Boolean resize;
    private Long maximumSize;

    private List<String> excludeExtensionList = Arrays.asList("jsp", "asp", "java", "cpp", "py", "js", "html", "css", "sh", "exe");
    private List<String> resolutions = Arrays.asList("");
    private Map<String,Integer> heightWidthTable = Map.of("480", 720, "720", 1280, "1080", 1920, "1440", 2560);

    public FileUpload(String filePath, String fileUrl, Boolean resize, Long maximumSize) {
        this.filePath = filePath;
        this.fileUrl = fileUrl;
        this.resize = resize;
        this.maximumSize = maximumSize;
    }


    public List<FileResult> upload(Boolean isImage, MultipartFile... multipartFile) throws FileException {
         Long excludeCount =  excludeExtensionList.stream().filter(item->{
             Long result = Arrays.stream(multipartFile).filter(item2-> item2.getContentType().equals(item)).count();
             return result>0;
         }).count();
         if(excludeCount>0){
            throw new FileException(FileExceptions.NOT_ALLOWED);
         }
         if(Arrays.stream(multipartFile).filter(item->item.getSize()>maximumSize*1024).count()>0){
             throw new FileException(FileExceptions.EXCEED_MAXIMUM_SIZE);
         }
         if(resize&&isImage){
             this.resolutions = Arrays.asList("480", "720", "1080", "1440","origin");
             this.resolutions.stream().forEach(item->mkdir(item));
            return  resizedMake( resolutions, multipartFile);
         }
        return makeFiles(multipartFile);
    }
    private String getUUID(){
        return UUID.randomUUID().toString();
    }
    private String getDateFormatPath(){
        return LocalDate.now().format(DateTimeFormatter.ofPattern("YYYYMMdd"));
    }
    private void mkdir(String folderName){
        File file = new File(filePath+"/"+folderName);
        if(!file.exists()){
            file.mkdirs();
        }
        String os = System.getProperty("os.name").toLowerCase();
        if(!os.contains("window")){
            file.setReadable(true);
            file.setWritable(true);
        }
    }
    private List<FileResult> makeFiles(MultipartFile ...files) throws FileException {
        String datePath = getDateFormatPath();
        String encryptedFileName = getUUID();
        List<FileResult> result = new ArrayList<>();
        mkdir(this.filePath+"/"+datePath);
        for(MultipartFile file : files){
            String originalFileName = file.getOriginalFilename();
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String storedFileName = datePath+"/"+encryptedFileName+"."+extension;
            String contentType = file.getContentType();
            Long fileSize = file.getSize();
            result.add(FileResult.builder().fileUrl(this.fileUrl).fileSize(fileSize)
                    .contentType(contentType).originalFileName(originalFileName).storedFileName(storedFileName).build());
            File f = new File(this.filePath+"/"+storedFileName);
            try(FileOutputStream fileOutputStream = new FileOutputStream(f);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)
            ){
                bufferedOutputStream.write(file.getBytes());
            } catch (IOException e){
                throw new FileException(FileExceptions.FILE_SAVE_FAIL);
            }
        }
        return result;
    }

    private List<FileResult> resizedMake( List<String> size, MultipartFile ...files) throws FileException {
        String datePath = getDateFormatPath();
        String encryptedFileName = getUUID();
        List<FileResult> result = new ArrayList<>();

        for(MultipartFile file: files){
            String originalFileName = file.getOriginalFilename();
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            String storedFileName = datePath+"/"+encryptedFileName+"."+extension;
            String contentType = file.getContentType();
            Long fileSize = file.getSize();
            result.add(FileResult.builder().fileUrl(this.fileUrl).fileSize(fileSize)
                    .contentType(contentType).originalFileName(originalFileName).storedFileName(storedFileName).build());
            try {
                BufferedImage bufferedImage = ImageIO.read(file.getInputStream());
                int type = bufferedImage.getType()==0? BufferedImage.TYPE_INT_ARGB:bufferedImage.getType();
                for (String sizePiece : size) {
                    String path = this.filePath + "/" + sizePiece + "/" + datePath + "/" + encryptedFileName + "." + extension;
                    File f = new File(path);
                    ImageIO.write(resize(bufferedImage, sizePiece, type),extension, f);
                }
            } catch (IOException e) {
                throw new FileException(FileExceptions.FILE_SAVE_FAIL);
            }
        }
        return result;
    }
    private BufferedImage resize( BufferedImage original, String size, int type){
        if(!size.equals("origin")){
            BufferedImage resizedImage = new BufferedImage(heightWidthTable.get(size), Integer.parseInt(size), type);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(original, 0,0,heightWidthTable.get(size), Integer.parseInt(size), null);
            graphics2D.dispose();
            return resizedImage;
        } else {
            return original;
        }
    }

    public class FileException extends Exception{
        private Integer code;
        private String msg;
        public FileException(FileExceptions exceptions) {
            super(exceptions.getMsg());
            this.code = exceptions.getCode();
            this.msg = exceptions.getMsg();
        }
    }
    enum  FileExceptions {
        NOT_ALLOWED(-9999, "해당 형식의 파일은 업로드 할 수 없습니다."),
        EXCEED_MAXIMUM_SIZE(-9999, "파일 사이즈가 너무 큽니다."),
        FILE_SAVE_FAIL(-9999, "파일 저장에 실패했습니다.");
        Integer code;
        String msg;

        public Integer getCode() {
            return code;
        }
        public String getMsg() {
            return msg;
        }
        FileExceptions(Integer code, String msg){
            this.code = code;
            this.msg = msg;
        }
    }
}


