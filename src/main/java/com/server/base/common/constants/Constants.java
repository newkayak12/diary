package com.server.base.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    public static String PROJECT_NAME;
    public static String SALT_VALUE;
    public static Boolean IS_DEV_MODE;

    @Value("${CONSTANTS.PROJECTNAME}")
    public void setProjectName(String _projectName){
        PROJECT_NAME=_projectName;
    }
    @Value("${CONSTANTS.SALTVALUE}")
    public void setSaltValue(String _saltValue) { SALT_VALUE = _saltValue; }
    @Value("${CONSTANTS.ISDEVMODE}")
    public void setIsDevMode(Boolean _is_dev_mode){ IS_DEV_MODE = _is_dev_mode; }
}
