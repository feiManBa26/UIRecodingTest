package utils;

/**
 * Created by ejiang on 2017-07-13.
 */
public class FileTypeUtils {
    // 创建图片类型数组
    private static String img[] = {"bmp", "jpg", "jpeg", "png", "tiff", "gif", "pcx", "tga", "exif", "fpx", "svg", "psd",
            "cdr", "pcd", "dxf", "ufo", "eps", "ai", "raw", "wmf"};
    // 创建文档类型数组
    private static String document[] = {"txt", "doc", "docx", "xls", "htm", "html", "jsp", "rtf", "wpd", "pdf", "ppt"};
    // 创建视频类型数组
    private static String video[] = {"mp4", "avi", "mov", "wmv", "asf", "navi", "3gp", "mkv", "f4v", "rmvb", "webm"};
    // 创建音乐类型数组
    private static String music[] = {"mp3", "wma", "wav", "mod", "ra", "cd", "md", "asf", "aac", "vqf", "ape", "mid", "ogg",
            "m4a", "vqf"};

    public static String getFileType(String fileName) {
        if (fileName == null) return FileType.ERRORNULL.toString();
        // 获取文件后缀名并转化为写，用于后续比较
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
        for (int i = 0; i < img.length; i++) {
            if (img[i].equals(fileType)) {
                return FileType.IMAGE.toString();
            }
        }

        for (int i = 0; i < document.length; i++) {
            if (document[i].equals(fileType)) {
                return FileType.DOCUMENT.toString();
            }
        }

        for (int i = 0; i < video.length; i++) {
            if (video[i].equals(fileType)) {
                return FileType.VIDEO.toString();
            }
        }

        for (int i = 0; i < music.length; i++) {
            if (music[i].equals(fileType)) {
                return FileType.MUSIC.toString();
            }
        }
        return FileType.ONTHER.toString();
    }

    public enum FileType {
        ERRORNULL, IMAGE, VIDEO, DOCUMENT, MUSIC, ONTHER;
    }
}
