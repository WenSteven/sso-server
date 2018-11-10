package cn.wenqi.oauth2.server.config;

import org.springframework.beans.propertyeditors.CustomCollectionEditor;

import java.util.Collection;

/**
 * @author wenqi
 */
public class SplitCollectionEditor extends CustomCollectionEditor {

    private final Class<? extends Collection> collectionType;
    private final String splitRegex;

    public SplitCollectionEditor(Class<? extends Collection> collectionType, String splitRegex) {
        super(collectionType, true);
        this.collectionType = collectionType;
        this.splitRegex = splitRegex;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null || text.isEmpty()) {
            super.setValue(super.createCollection(this.collectionType, 0));
        } else {
            super.setValue(text.split(splitRegex));
        }
    }
}
