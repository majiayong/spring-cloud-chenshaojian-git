package com.demo.merchant.restapi.controller;

import com.demo.merchant.domain.entity.Kind;
import com.demo.merchant.domain.service.KindService;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.CopyUtil;
import com.demo.merchant.object.KindQo;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author yangyueming
 */
@RestController
@RequestMapping("/kind")
@Slf4j
public class KindController {
    @Autowired
    private KindService kindService;

    @RequestMapping("/{id}")
    public CompletableFuture<String> findById(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> kindService.findOne(id))
                .thenApply(resource -> {
                    return new Gson().toJson(resource);
                });
    }

    @RequestMapping("/list")
    public CompletableFuture<String> getList() {
        return CompletableFuture.supplyAsync(() -> {
            List<Kind> models = kindService.findAll();
            return new Gson().toJson(models);
        });
    }

    @RequestMapping(value = "/page")
    public CompletableFuture<String> findPage(Integer index, Integer size, String name) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                KindQo kindQo = new KindQo();

                if (CommonUtils.isNotNull(index)) {
                    kindQo.setPage(index);
                }
                if (CommonUtils.isNotNull(size)) {
                    kindQo.setSize(size);
                }
                if (CommonUtils.isNotNull(name)) {
                    kindQo.setName(name);
                }

                Page<Kind> modelVos = kindService.findAll(kindQo);

                Map<String, Object> page = new HashMap<>();
                page.put("content", modelVos.getContent());
                page.put("totalPages", modelVos.getTotalPages());
                page.put("totalelements", modelVos.getTotalElements());

                return new Gson().toJson(page);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public CompletableFuture<String> save(@RequestBody KindQo kindQo) {
        return CompletableFuture.supplyAsync(() -> {
            Kind kind = CopyUtil.copy(kindQo, Kind.class);

            kindService.save(kind);

            log.info("新增->ID=" + kind.getId());
            return "1";
        });
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public CompletableFuture<String> update(@RequestBody KindQo kindQo) {
        return CompletableFuture.supplyAsync(() -> {
            Kind kind = CopyUtil.copy(kindQo, Kind.class);

            kindService.save(kind);

            log.info("修改->ID=" + kind.getId());
            return "1";
        });
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public CompletableFuture<String> delete(@PathVariable Long id) {
        return CompletableFuture.supplyAsync(() -> {
            kindService.delete(id);
            log.info("删除->ID=" + id);
            return "1";
        });
    }
}
