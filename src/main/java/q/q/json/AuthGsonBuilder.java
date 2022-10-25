package q.q.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

/**
 * .
 *
 * @author chanjarster
 */
public class AuthGsonBuilder {
    private static final GsonBuilder INSTANCE = new GsonBuilder();
    private static volatile Gson GSON_INSTANCE;


    public static Gson create() {
        if (Objects.isNull(GSON_INSTANCE)) {
            synchronized (INSTANCE) {
                if (Objects.isNull(GSON_INSTANCE)) {
                    GSON_INSTANCE = INSTANCE.create();
                }
            }
        }
        return GSON_INSTANCE;
    }

}
