package method;

/**
 * Created by ejiang on 2017-07-14.
 */
public class TransferFile {
    private String fileName;
    private double fileSize;
    private int fileType;
    private int bagSize;
    private int bagCont;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getFileSize() {
        return fileSize;
    }

    public void setFileSize(double fileSize) {
        this.fileSize = fileSize;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }

    public int getBagSize() {
        return bagSize;
    }

    public void setBagSize(int bagSize) {
        this.bagSize = bagSize;
    }

    public int getBagCont() {
        return bagCont;
    }

    public void setBagCont(int bagCont) {
        this.bagCont = bagCont;
    }

    @Override
    public String toString() {
        return "TransferFile{" +
                "fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileType=" + fileType +
                ", bagSize=" + bagSize +
                ", bagCont=" + bagCont +
                '}';
    }
}
