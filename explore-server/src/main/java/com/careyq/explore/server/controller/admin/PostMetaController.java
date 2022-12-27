package com.careyq.explore.server.controller.admin;

import com.careyq.explore.common.vo.Result;
import com.careyq.explore.server.entity.PostMeta;
import com.careyq.explore.server.service.PostMetaService;
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
@RequestMapping("/api/post/meta")
public class PostMetaController {

    private final PostMetaService postMetaService;

    /**
     * 保存
     *
     * @param postMeta 实体
     * @return 结果
     */
    @PostMapping("/save")
    public Result<Boolean> saveMeta(@RequestBody @Validated PostMeta postMeta) {
        return postMetaService.saveMeta(postMeta);
    }

    /**
     * 获取分类列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public Result<List<CategoryVO>> getCategories() {
        return Result.success(postMetaService.getCategories());
    }

    /**
     * 删除分类
     *
     * @return 结果
     */
    @DeleteMapping("/del")
    public Result<Boolean> delCategory(@RequestParam Long id) {
        return postMetaService.delCategory(id);
    }
}

