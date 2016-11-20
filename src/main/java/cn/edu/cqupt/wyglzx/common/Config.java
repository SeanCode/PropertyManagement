package cn.edu.cqupt.wyglzx.common;

public class Config {

    public static final String[] PATH_GUEST_CAN_ACCESS = new String[]{};

    public static final boolean IS_DEBUG = true;

    public static final int RECORD_WARNING_VALUE = 100;

    public static final String DEBUG_DB_USER = "root";

    public static final String IMG_PATH_DEBUG = "/users/cc/Dev/web/Sites/static/res/img";

    public static final String IMG_PATH = "D:/web/app/static/res/img";

    public static final String ATTACHMENT_PATH_DEBUG = "/users/cc/Dev/web/Sites/static/res/file";

    public static final String ATTACHMENT_PATH = "D:/web/app/static/res/file";

    public static String getImgPath() {
        if (IS_DEBUG) {
            return IMG_PATH_DEBUG;
        }
        return IMG_PATH;
    }

    public static String getAttachmentPath() {
        if (IS_DEBUG) {
            return ATTACHMENT_PATH_DEBUG;
        }
        return ATTACHMENT_PATH;
    }

}
