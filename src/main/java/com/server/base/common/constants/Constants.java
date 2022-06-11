package com.server.base.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Constants {
    public static String PROJECT_NAME;
    public static Boolean IS_DEV_MODE;
    public static String SALT_VALUE;
    public static String ACCESS_TOKEN;
    public static String REFRESH_SALT_VALUE;
    public static String REFRESH_TOKEN;
    public static List<String> API_PATH;
    public static String IMAGE_URL;
    public static String FILE_PATH;
    public static Boolean RESIZE;
    public static Long FILE_MAXIMUM_SIZE;
    public static Long ACCESS_TOKEN_TIME;


    //static
    @Value("${CONSTANTS.PROJECT_NAME}")
    public void setProjectName(String _projectName){
        PROJECT_NAME=_projectName;
    }
    @Value("${CONSTANTS.EXCLUDE_API_PATHS}")
    public void setApiPath(String _apiPath){ API_PATH = Arrays.asList(_apiPath.split(",")); }
    @Value("${CONSTANTS.SALT_VALUE}")
    public void setSaltValue(String _saltValue) { SALT_VALUE = _saltValue; }
    @Value("${CONSTANTS.ACCESS_TOKEN_NAME}")
    public void setAccessToken(String _access_token){ACCESS_TOKEN=_access_token; }
    @Value("${CONSTANTS.ACCESS_TOKEN_TIME}")
    public void setAccessTokenTime(Long _access_token_time) {ACCESS_TOKEN_TIME  = _access_token_time*1000*60L;}
    @Value("${CONSTANTS.REFRESH_SALT_VALUE}")
    public void setRefreshSaltValue(String _refresh_salt_value){REFRESH_SALT_VALUE=_refresh_salt_value; }
    @Value("${CONSTANTS.REFRESH_TOKEN_NAME}")
    public void setRefreshToken(String _refresh_token){REFRESH_TOKEN=_refresh_token;}

    //depends on profile
    @Value("${CONSTANTS.IS_DEV_MODE}")
    public void setIsDevMode(Boolean _is_dev_mode){ IS_DEV_MODE = _is_dev_mode; }
    @Value("${CONSTANTS.IMAGE_URL}")
    public void setImageUrl(String _image_url) {IMAGE_URL = _image_url;}
    @Value("${CONSTANTS.FILE_PATH}")
    public void setFilePath(String _file_path) {FILE_PATH = _file_path;}
    @Value("${CONSTANTS.RESIZE}")
    public void setRESIZE(Boolean resize) {Constants.RESIZE = resize;}
    @Value("${CONSTANTS.FILE_MAXIMUM_SIZE}")
    public void setFileMaximumSize(Long _file_maximum_size) { FILE_MAXIMUM_SIZE = _file_maximum_size;}





//    TEST - CONSTANTS
    public static String TEST_ACCESS_TOKEN;
    public static String TEST_REFRESH_TOKEN;
    public static String TEST_PREFIX;

    @Value("${TEST.ACCESS_TOKEN}")
    public void setTestAccessToken(String _test_access_token){
        TEST_ACCESS_TOKEN = _test_access_token;
    }
    @Value("${TEST.REFRESH_TOKEN}")
    public void setTestRefreshToken(String _test_refresh_token){
        TEST_REFRESH_TOKEN = _test_refresh_token;
    }
    @Value("${TEST.PREFIX}")
    public void setTestPrefix(String _test_prefix){
        TEST_PREFIX = _test_prefix;
    }

}
