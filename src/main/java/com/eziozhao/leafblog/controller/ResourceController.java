package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonPage;
import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.mbg.entity.Resource;
import com.eziozhao.leafblog.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody Resource resource) {
        int res = resourceService.add(resource);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam(value = "id") Integer id) {
        int res = resourceService.delete(id);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody Resource resource) {
        if (resource.getId() == null) {
            return CommonResult.paramError();
        }
        int res = resourceService.update(resource);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @GetMapping("/{id}")
    public CommonResult<Resource> getInfo(@PathVariable Integer id) {
        Resource resource = resourceService.getInfo(id);
        return CommonResult.success(resource);
    }

    @GetMapping("/listAll")
    public CommonResult<List<Resource>> listAll() {
        List<Resource> resourceList = resourceService.listAll();
        return CommonResult.success(resourceList);
    }

    @GetMapping("/list")
    public CommonResult<CommonPage<Resource>> getResourceList(@RequestParam(value = "keyword", required = false) String keyword,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<Resource> resourceList = resourceService.getResourceList(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }


}
