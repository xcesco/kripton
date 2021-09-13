package sqlite.git104;

import com.abubusoft.kripton.annotation.BindType;

@BindType
public class FileInfo {
  private final String fileName;
  private final String secret;
  private final DocumentInfo info;

  public FileInfo(String fileName, String secret, DocumentInfo info) {
    this.fileName = fileName;
    this.secret = secret;
    this.info = info;
  }

  public DocumentInfo getInfo() {
    return info;
  }

  public String getFileName() {
    return fileName;
  }

  public String getSecret() {
    return secret;
  }
}
