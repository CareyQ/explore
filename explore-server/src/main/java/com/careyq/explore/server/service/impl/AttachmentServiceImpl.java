package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.enums.IEnum;
import com.careyq.explore.common.exception.UserException;
import com.careyq.explore.common.util.FileUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.AttachmentBatchOperateDTO;
import com.careyq.explore.server.dto.AttachmentPageDTO;
import com.careyq.explore.server.enmus.BatchOperateTypeEnum;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.entity.Attachment;
import com.careyq.explore.server.mapper.AttachmentMapper;
import com.careyq.explore.server.service.AttachmentService;
import com.careyq.explore.server.vo.AttachmentVO;
import com.careyq.explore.server.vo.AttachmentPageVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 文件表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Slf4j
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {

    private static final String LOCATION = "D:\\Files\\";

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadFile(MultipartFile file, Long categoryId,  FilePathEnum pathEnum) {
        String filename = file.getOriginalFilename();
        if (StrUtil.isBlank(filename)) {
            throw new UserException("文件名称不能为空");
        }
        Attachment attachment = new Attachment();
        attachment.setName(filename)
                .setCategoryId(categoryId)
                .setType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).getType())
                .setSize(file.getSize());
        attachment.builderPath(pathEnum.getPath(), filename, FileUtil.extName(filename));

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (Objects.nonNull(image)) {
                attachment.setHeight(image.getWidth())
                        .setWidth(image.getHeight());
            }
        } catch (IOException e) {
            log.error("文件转换 image 异常，文件名：{}", file.getOriginalFilename(), e);
            throw new UserException("文件转换图片失败");
        }
        attachment.insert();
        File localFile = FileUtil.buildFile(LOCATION + attachment.getPath());
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            log.error("文件写入本地异常", e);
            throw new UserException("文件写入本地失败");
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex(String.valueOf(System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8)));


    }

    public static String getBasename(String filename) {
        Assert.hasText(filename, "Filename must not be blank");

        // Find the last slash
        int separatorLastIndex = StringUtils.lastIndexOf(filename, File.separatorChar);

        if (separatorLastIndex == filename.length() - 1) {
            return StringUtils.EMPTY;
        }

        if (separatorLastIndex >= 0 && separatorLastIndex < filename.length() - 1) {
            filename = filename.substring(separatorLastIndex + 1);
        }

        // Find last dot
        int dotLastIndex = StringUtils.lastIndexOf(filename, '.');

        String[] split = filename.split("\\.");

        List<String> extList = Arrays.asList("gz", "bz2");

        if (extList.contains(split[split.length - 1]) && split.length >= 3) {
            return filename.substring(0, filename.substring(0, dotLastIndex).lastIndexOf('.'));
        }

        if (dotLastIndex < 0) {
            return filename;
        }

        return filename.substring(0, dotLastIndex);
    }

    @Override
    public boolean isExistByCategory(Long categoryId) {
        return baseMapper.selectExistByCategory(categoryId) != null;
    }

    @Override
    public IPage<AttachmentPageVO> getAttachmentPage(AttachmentPageDTO dto) {
        return baseMapper.selectAttachmentPage(new Page<>(dto.getCurrent(), dto.getSize()), dto);
    }

    @Override
    public AttachmentVO getAttachment(Long id) {
        return baseMapper.selectAttachment(id);
    }

    @Override
    public Result<Boolean> batchOperate(AttachmentBatchOperateDTO dto) {
        BatchOperateTypeEnum typeEnum = IEnum.codeOf(BatchOperateTypeEnum.class, dto.getOperationType());
        switch (typeEnum) {
            case DEL -> this.removeBatchByIds(dto.getIds());
            case MOVE -> {
                if (dto.getTargetCategoryId() == null) {
                    throw new UserException("未指定附件分类");
                }
                this.lambdaUpdate()
                        .set(Attachment::getCategoryId, dto.getTargetCategoryId())
                        .in(Attachment::getId, dto.getIds());
            }
            default -> throw new IllegalStateException("未知的操作: " + dto.getOperationType());
        }
        return Result.success("操作成功");
    }

}
