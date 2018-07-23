package cn.wenqi.oauth2.service.exception;

/**
 * @author wenqi
 * @since v
 */
public class StorageFileNotFoundException extends RuntimeException {

    public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
