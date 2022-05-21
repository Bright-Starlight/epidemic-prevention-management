package com.parachute.mian.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.parachute.mian.entity.Carrier;
import com.parachute.mian.service.CarrierService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Carrier)表控制层
 *
 * @author makejava
 * @since 2022-05-21 15:31:22
 */
@RestController
@RequestMapping("carrier")
public class CarrierController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private CarrierService carrierService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param carrier 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Carrier> page, Carrier carrier) {
        return success(this.carrierService.page(page, new QueryWrapper<>(carrier)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.carrierService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param carrier 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Carrier carrier) {
        return success(this.carrierService.save(carrier));
    }

    /**
     * 修改数据
     *
     * @param carrier 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Carrier carrier) {
        return success(this.carrierService.updateById(carrier));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.carrierService.removeByIds(idList));
    }
}

