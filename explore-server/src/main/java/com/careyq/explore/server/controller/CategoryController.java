package com.careyq.explore.server.controller;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.PostCategoryTag;
import com.careyq.explore.server.service.PostCategoryTagService;
import com.careyq.explore.server.service.CommonService;
import com.careyq.explore.server.vo.CategoryVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 分类表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-08-19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CommonService<PostCategoryTag> commonService;
    private final PostCategoryTagService postCategoryTagService;

    /**
     * 保存分类
     *
     * @param postCategoryTag 分类
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveCategory(@RequestBody @Validated PostCategoryTag postCategoryTag) {
        return commonService.saveEntity(postCategoryTag);
    }

    /**
     * 获取分类列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<List<CategoryVO>> getCategories() {
        return Result.success(postCategoryTagService.getCategories());
    }

    /**
     * 删除分类
     *
     * @return 结果
     */
    @DeleteMapping("/del")
    public Result<Boolean> delCategory(@RequestParam Long id) {
        return postCategoryTagService.delCategory(id);
    }
}

