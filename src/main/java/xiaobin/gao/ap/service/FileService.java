/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xiaobin.gao.ap.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author gao.xiaob
 */

@Service("fileService")
public class FileService {

    public static final String SAVE_LOCATION = "/Users/gao.xiaob/Desktop/airplayer_img";

    public boolean saveFile(MultipartFile multipartFile) {
        boolean result = false;
        //set the saved location and create a directory location
        String fileName = multipartFile.getOriginalFilename();
        String location = SAVE_LOCATION;
        File pathFile = new File(location);
        //check if directory exist, if not, create directory
        if (!pathFile.exists()) {
            pathFile.mkdir();
        }

        //create the actual file
        pathFile = new File(location + fileName);
        //save the actual file
        try {
            multipartFile.transferTo(pathFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
