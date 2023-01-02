package com.careyq.explore.server.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.careyq.explore.common.enums.IEnum;
import com.careyq.explore.common.exception.UserException;
import com.careyq.explore.common.util.CollUtil;
import com.careyq.explore.common.util.ConfigUtil;
import com.careyq.explore.common.util.FileUtil;
import com.careyq.explore.common.util.StrUtil;
import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.dto.AttachmentBatchOperateDTO;
import com.careyq.explore.server.dto.ResourcePageDTO;
import com.careyq.explore.server.enmus.BatchOperateTypeEnum;
import com.careyq.explore.server.enmus.ConfigEnum;
import com.careyq.explore.server.enmus.FilePathEnum;
import com.careyq.explore.server.entity.Resource;
import com.careyq.explore.server.mapper.ResourceMapper;
import com.careyq.explore.server.service.ResourceService;
import com.careyq.explore.server.vo.ResourcePageVO;
import com.careyq.explore.server.vo.ResourceVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@Slf4j
@Service
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadFile(MultipartFile file, Long categoryId,  FilePathEnum pathEnum) {
        if (categoryId == null) {
            categoryId = ConfigUtil.getResourceCategory();
        }
        String filename = file.getOriginalFilename();
        if (StrUtil.isBlank(filename)) {
            throw new UserException("文件名称不能为空");
        }
        String resourcePath = ConfigUtil.getConfig(ConfigEnum.RESOURCE_PATH.getCode());
        if (StrUtil.isBlank(resourcePath)) {
            throw new UserException("文件名称不能为空");
        }
        Resource resource = new Resource();
        resource.setName(filename)
                .setCategoryId(categoryId)
                .setType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).getType())
                .setSize(file.getSize());
        resource.builderPath(pathEnum.getPath(), filename, FileUtil.extName(filename));

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (Objects.nonNull(image)) {
                resource.setHeight(image.getWidth())
                        .setWidth(image.getHeight());
            }
        } catch (IOException e) {
            log.error("文件转换 image 异常，文件名：{}", file.getOriginalFilename(), e);
            throw new UserException("文件转换图片失败");
        }
        resource.insert();
        File localFile = FileUtil.buildFile(resourcePath + resource.getPath());
        try {
            file.transferTo(localFile);
        } catch (IOException e) {
            log.error("文件写入本地异常", e);
            throw new UserException("文件写入本地失败");
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchUploadFile(List<MultipartFile> files, Long categoryId) {
        if (CollUtil.isEmpty(files)) {
            throw new UserException("上传的文件不能为空");
        }
        if (categoryId == null) {
            categoryId = ConfigUtil.getResourceCategory();
        }
        String resourcePath = ConfigUtil.getConfig(ConfigEnum.RESOURCE_PATH.getCode());
        if (StrUtil.isBlank(resourcePath)) {
            throw new UserException("请先配置资源存储路径");
        }

        List<Resource> resources = new ArrayList<>();
        Map<Integer, MultipartFile> tempMap = new HashMap<>(files.size());
        int index = 0;
        for (MultipartFile file : files) {
            String filename = file.getOriginalFilename();
            if (StrUtil.isBlank(filename)) {
                throw new UserException("文件名称不能为空");
            }
            if (filename != null && filename.length() > 50) {
                throw new UserException("文件名称不可超过 50 字");
            }

            Resource resource = new Resource();
            resource.setName(filename)
                    .setCategoryId(categoryId)
                    .setType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())).getType())
                    .setSize(file.getSize())
                    .setIndex(index);
            resource.builderPath(FilePathEnum.RESOURCE.getPath() + categoryId + "/", filename, FileUtil.extName(filename));

            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                if (Objects.nonNull(image)) {
                    resource.setHeight(image.getWidth())
                            .setWidth(image.getHeight());
                }
            } catch (IOException e) {
                log.error("文件转换 image 异常，文件名：{}", file.getOriginalFilename(), e);
            }
            tempMap.put(index, file);
            index++;


            resources.add(resource);
        }


        boolean res = this.saveBatch(resources);
        if (res) {
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            for (Resource resource : resources) {
                MultipartFile file = tempMap.get(resource.getIndex());
                CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                    File localFile = FileUtil.buildFile(resourcePath + resource.getPath());
                    try {
                        file.transferTo(localFile);
                    } catch (IOException e) {
                        log.error("文件写入本地异常", e);
                        throw new UserException("文件写入本地失败");
                    }
                });
                futures.add(future);
            }
            futures.forEach(CompletableFuture::join);
        }
        return res;
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
    public IPage<ResourcePageVO> getPage(ResourcePageDTO dto) {
        return baseMapper.selectAttachmentPage(new Page<>(dto.getCurrent(), dto.getSize()), dto);
    }

    @Override
    public ResourceVO getDetail(Long id) {
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
                        .set(Resource::getCategoryId, dto.getTargetCategoryId())
                        .in(Resource::getId, dto.getIds());
            }
            default -> throw new IllegalStateException("未知的操作: " + dto.getOperationType());
        }
        return Result.success("操作成功");
    }

}
