package com.example.demo.file;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@Component
public class FileStore {
    @Value("${file.dir}")
    private String fileDir;

    public String getFullPath(String filename){
        return fileDir+filename;
    }

    public String saveImage(MultipartFile image) throws IOException {
        //이름, 경로설정 -> 경로와 이름을 board에 set해서 반환
        //데이터에 저장은 컨트롤러에서
        if(image.isEmpty()){
            return null;
        }
        //로컬에 이미지 저장
        String imageStoreName= UUID.randomUUID()+"_"+image.getOriginalFilename();
        image.transferTo(new File(getFullPath(imageStoreName)));

        return imageStoreName;
    }

}
