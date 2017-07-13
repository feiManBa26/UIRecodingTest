import org.junit.Test;

import utils.FileTypeUtils;

import static org.junit.Assert.*;

/**
 * Created by ejiang on 2017-07-13.
 */
public class FileTypeUtilsTest {
    @Test
    public void getFileType() throws Exception {
        System.out.println(FileTypeUtils.getFileType("柳树姑娘.mp3"));
        System.out.println(FileTypeUtils.getFileType("柳树姑娘.txt"));
        System.out.println(FileTypeUtils.getFileType("柳树姑娘.mp4"));
        System.out.println(FileTypeUtils.getFileType("柳树姑娘.xslx"));
    }

}