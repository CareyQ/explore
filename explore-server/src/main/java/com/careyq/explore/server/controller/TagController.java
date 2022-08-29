package com.careyq.explore.server.controller;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Tag;
import com.careyq.explore.server.service.CommonService;
import com.careyq.explore.server.service.TagService;
import com.careyq.explore.server.vo.TagVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 标签表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/tag")
public class TagController {

    private final CommonService<Tag> commonService;
    private final TagService tagService;

    /**
     * 标签
     *
     * @param tag 标签
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveCategory(@RequestBody @Validated Tag tag) {
        return commonService.saveEntity(tag);
    }

    /**
     * 获取标签列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<List<TagVO>> getTags() {
        return Result.success(tagService.getTags());
    }
}

