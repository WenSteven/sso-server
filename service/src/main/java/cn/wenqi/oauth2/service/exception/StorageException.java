package cn.wenqi.oauth2.service.exception;

/**
 * @author wenqi
 * @since v1.0.1
 */
public class StorageException extends RuntimeException{

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
