package com.petrik.rop.util;

import com.petrik.rop.exception.FieldException;
import com.petrik.rop.exception.GroupException;

public class ExceptionUtils {

    public static String getFieldException(String field) {
        return new FieldException(field).getMessage();
    }

    public static String getFieldException(String field, String additionalMessage) {
        return new FieldException(field, additionalMessage).getMessage();
    }

    public static String getGroupException(String group) {
        return new GroupException(group).getMessage();
    }

    public static String getGroupException(String group, String additionalMessage) {
        return new GroupException(group, additionalMessage).getMessage();
    }
}
