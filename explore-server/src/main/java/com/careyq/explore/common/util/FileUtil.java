package com.careyq.explore.common.util;

import lombok.experimental.UtilityClass;

import java.io.File;

/**
 * 文件工具类
 *
 * @author careyq
 * @since 2022-08-19
 */
@UtilityClass
public class FileUtil {

    public void buildFile(String path) {
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            boolean res = file.getParentFile().mkdirs();
            if (!res) {
                throw new RuntimeException("文件目录创建失败");
            }
        }

    }

}
