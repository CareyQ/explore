package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.util.FileUtil;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.entity.Attachment;
import com.careyq.explore.server.mapper.AttachmentMapper;
import com.careyq.explore.server.service.AttachmentService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {

    private static final String LOCATION = "E:\\Files\\";

    @Override
    public String uploadFile(MultipartFile file, FilePathEnum pathEnum) {
        Attachment attachment = new Attachment();
        attachment.setName(file.getOriginalFilename())
                .setType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).getType())
                .setPath(pathEnum.getCode())
                .setSize(file.getSize());

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image != null) {
                attachment.setHeight(image.getWidth())
                        .setWeight(image.getHeight());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        attachment.insert();
        FileUtil.buildFile(LOCATION + pathEnum.getPath() + "/" + file.getOriginalFilename());

        // String filePath = LOCATION + path;
        return null;
    }

    public static void main(String[] args) {
        File file = new File(LOCATION + "avatar/a.jpg");
        try {
            BufferedImage image = ImageIO.read(file);
            if (image != null) {
                int width = image.getWidth();
                int height = image.getHeight();
                System.out.println(width);
                System.out.println(height);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
