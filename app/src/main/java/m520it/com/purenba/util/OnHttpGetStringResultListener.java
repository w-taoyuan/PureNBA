package m520it.com.purenba.util;

import java.io.IOException;

/**
 * Created by conan on 2017/4/27.
 */

public interface OnHttpGetStringResultListener {
    void onFail(IOException e);
    void onSuccess(String result);
}