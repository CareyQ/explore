package com.careyq.explore.server.controller.admin;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.ResourceCategory;
import com.careyq.explore.server.service.ResourceCategoryService;
import com.careyq.explore.server.vo.ResourceCategoryPageVO;
import com.careyq.explore.server.vo.ResourceCategoryVO;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资源分类表 控制层
 * </p>
 *
 * @author CareyQ
 * @since 2022-09-13
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/resource/category")
public class ResourceCategoryController {

    private final ResourceCategoryService resourceCategoryService;

    /**
     * 保存资源分类
     *
     * @param category 资源分类
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveResourceCategory(@RequestBody @Validated ResourceCategory category) {
        return resourceCategoryService.saveResourceCategory(category);
    }

    /**
     * 保存资源分类
     *
     * @param id 分类 ID
     * @return 结果
     */
    @DeleteMapping("/del")
    public Result<Boolean> delResourceCategory(@RequestParam Long id) {
        return resourceCategoryService.delResourceCategory(id);
    }

    /**
     * 获取资源分类
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<List<ResourceCategoryPageVO>> getList() {
        return Result.success(resourceCategoryService.getList());
    }

    /**
     * 获取资源分类详情
     *
     * @param id 分类 ID
     * @return 详情
     */
    @GetMapping("/detail")
    public Result<ResourceCategoryVO> getDetail(@RequestParam Long id) {
        return Result.success(resourceCategoryService.getDetail(id));
    }
}

