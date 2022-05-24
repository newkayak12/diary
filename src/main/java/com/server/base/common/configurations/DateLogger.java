package com.server.base.common.configurations;

import com.server.base.common.constants.Constants;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.CommandLineRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateLogger implements DisposableBean {
    @Override
    public void destroy() throws Exception {
        if(Constants.IS_DEV_MODE){
            String path = FileSystems.getDefault().getPath("").toAbsolutePath().toString();
            File f = new File(path+"/src/main/java/com/server/base/common/configurations/jsonLog/log.json");
            if(!f.exists()){
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f);
            String date ="{\"date\": \""+LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss")).toString()+"\"}";
            fw.write(date);
            fw.close();
        }
    }
}
