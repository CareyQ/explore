package com.careyq.explore.common.util;

import com.careyq.explore.common.exception.UserException;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 文件工具类
 *
 * @author careyq
 * @since 2022-08-19
 */
@Slf4j
@UtilityClass
public class FileUtil {

    /**
     * 特殊后缀
     */
    private static final CharSequence[] SPECIAL_SUFFIX = {"tar.bz2", "tar.Z", "tar.gz", "tar.xz"};

    /**
     * 构建文件路径，对没有的文件夹、文件进行创建
     *
     * @param path 文件路径
     */
    public File buildFile(String path) {
        File file = new File(path);
        System.out.println(path);
        if (!file.getParentFile().exists()) {
            boolean res = file.getParentFile().mkdirs();
            if (!res) {
                log.error("文件目录构建失败，参数：{}", path);
                throw new UserException("文件目录创建失败");
            }
        }

        if (!file.exists()) {
            try {
                boolean res = file.createNewFile();
                if (!res) {
                    log.error("文件构建失败，参数：{}", path);
                    throw new UserException("文件构建失败");
                }
            } catch (IOException e) {
                log.error("文件构建出现异常，参数：{}", path, e);
                throw new UserException("文件构建失败");
            }
        }
        return file;
    }

    /**
     * 获取文件扩展名，不带 "."
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    public String extName(String fileName) {
        if (StrUtil.isBlank(fileName)) {
            return StrUtil.EMPTY;
        }
        int index = fileName.lastIndexOf(StrUtil.DOT);
        if (index == -1) {
            return StrUtil.EMPTY;
        } else {
            int secondToLastIndex = fileName.substring(0, index).lastIndexOf(StrUtil.DOT);
            String substr = fileName.substring(secondToLastIndex == -1 ? index : secondToLastIndex + 1);
            if (StrUtil.isContainsStr(substr, SPECIAL_SUFFIX)) {
                return substr;
            }

            String ext = fileName.substring(index + 1);
            // 扩展名中不能包含路径相关的符号
            return StrUtil.isContainsStr(ext, StrUtil.SLASH, StrUtil.BACKSLASH) ? StrUtil.EMPTY : ext;
        }
    }

    public static void main(String[] args) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1");
        objects.remove("1");
    }
}
