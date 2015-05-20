package com.example.opengles_training.app.constants;

/**
 * Created by Ufuk on 30-04-15.
 */
public final class Constants {

    /**
     * <p>Generally using for getting class name for logging. Simple usage:</p>
     * <code>Constants.TAG(this)</code>
     * @param context refers to the current object.
     * @return <code>context.getClass().getName()</code>
     */
    public static String TAG(Object context) {
        return context.getClass().getName();
    }

    /**
     * This method prevents even the native class from calling this constructor as well.
     */
    private Constants() {
        throw new AssertionError();
    }
}
