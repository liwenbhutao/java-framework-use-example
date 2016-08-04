package com.ht.test.brave.profile;

import com.google.common.base.Strings;
import com.ht.test.brave.profile.anotation.BraveAnnotation;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created on 16/8/1.
 *
 * @author hutao
 * @version 1.0
 */
@Getter
@AllArgsConstructor
public class BraveAnnotationConfig {
    private final boolean enable;
    private final String name;
    private final String funcName;

    public static BraveAnnotationConfig of(final BraveAnnotation annotation,
                                           final String funcName) {
        return new BraveAnnotationConfig(annotation.enable(),
                Strings.isNullOrEmpty(annotation.name()) ? funcName : annotation.name(), funcName);
    }
}
