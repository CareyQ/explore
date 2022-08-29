package com.careyq.explore.server.controller;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.Category;
import com.careyq.explore.server.service.CategoryService;
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

    private final CommonService<Category> commonService;
    private final CategoryService categoryService;

    /**
     * 保存分类
     *
     * @param category 分类
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveCategory(@RequestBody @Validated Category category) {
        return commonService.saveEntity(category);
    }

    /**
     * 获取分类列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<List<CategoryVO>> getCategories() {
        return Result.success(categoryService.getCategories());
    }

    /**
     * 分类排序交换
     *
     * @return 结果
     */
    @PutMapping("/exchange")
    public Result<Boolean> exchange(@RequestBody List<Long> ids) {
        return categoryService.exchange(ids);
    }
}

