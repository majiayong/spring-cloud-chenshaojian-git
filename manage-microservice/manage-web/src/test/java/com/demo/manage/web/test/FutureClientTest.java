package com.demo.manage.web.test;

import com.demo.manage.domain.config.JpaConfiguration;
import com.demo.manage.web.ManageWebApplication;
import com.demo.merchant.client.service.KindFuture;
import com.demo.merchant.client.service.ModelFuture;
import com.demo.merchant.client.service.ResourceFuture;
import com.demo.merchant.client.service.RoleFuture;
import com.demo.merchant.client.service.UserFuture;
import com.demo.merchant.client.util.TreeMapConvert;
import com.demo.merchant.object.KindQo;
import com.demo.merchant.object.ModelQo;
import com.demo.merchant.object.ResourceQo;
import com.demo.merchant.object.RoleQo;
import com.demo.merchant.object.UserQo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {JpaConfiguration.class, ManageWebApplication.class})
@SpringBootTest
@Slf4j
public class FutureClientTest {
    @Autowired
    private ModelFuture modelFuture;

    @Autowired
    private ResourceFuture resourceFuture;

    @Autowired
    private RoleFuture roleFuture;

    @Autowired
    private UserFuture userFuture;

    @Autowired
    private KindFuture kindFuture;


    //@Test
    public void updateRole() {
        RoleQo roleQo = new Gson().fromJson(roleFuture.findById(1L).join(), RoleQo.class);
        String sid = roleFuture.update(roleQo).join();
        assert new Integer(sid) > 0 : "update error";
        log.info("=============== sid={}", sid);
    }

    @Test
    public void getModelPage() {
        ModelQo modelQo = new ModelQo();
        modelQo.setSize(4);
        modelQo.setPage(0);

        String json = modelFuture.findPage(modelQo.getPage(), modelQo.getSize(), modelQo.getName()).join();

        Gson gson = TreeMapConvert.getGson();
        TreeMap<String, Object> page = gson.fromJson(json, new TypeToken<TreeMap<String, Object>>() {
        }.getType());

        Pageable pageable = new PageRequest(modelQo.getPage(), modelQo.getSize(), null);

        List<ModelQo> list = new ArrayList<>();

        if (page.get("content") != null)
            list = gson.fromJson(page.get("content").toString(), new TypeToken<List<ModelQo>>() {
            }.getType());
        String count = page.get("totalelements").toString();

        Page<Map<String, Object>> newPage = new PageImpl(list, pageable, new Long(count));

        assert newPage.getTotalElements() > 0;
        for (ModelQo model : list) {
            log.info("=============== name={}", model.getName());
        }
        log.info("===============pageno={}, total_pages={}, total_elements={}", newPage.getNumber(),
                newPage.getTotalPages(), newPage.getTotalElements());
    }

    //@Test
    public void getResourcePage() {
        ResourceQo resourceQo = new ResourceQo();
        resourceQo.setSize(4);
        resourceQo.setPage(0);

        String json = resourceFuture.findPage(resourceQo.getPage(), resourceQo.getSize(), resourceQo.getName()).join();

        Gson gson = TreeMapConvert.getGson();
        TreeMap<String, Object> page = gson.fromJson(json, new TypeToken<TreeMap<String, Object>>() {
        }.getType());

        Pageable pageable = new PageRequest(resourceQo.getPage(), resourceQo.getSize(), null);

        List<ResourceQo> list = new ArrayList<>();

        if (page.get("content") != null)
            list = gson.fromJson(page.get("content").toString(), new TypeToken<List<ResourceQo>>() {
            }.getType());
        String count = page.get("totalelements").toString();

        Page<Map<String, Object>> newPage = new PageImpl(list, pageable, new Long(count));

        assert newPage.getTotalElements() > 0;
        for (ResourceQo resource : list) {
            log.info("=============== name={}", resource.getName());
        }
        log.info("===============pageno={}, total_pages={}, total_elements={}", newPage.getNumber(),
                newPage.getTotalPages(), newPage.getTotalElements());
    }

    //@Test
    public void getRolePage() {
        RoleQo roleQo = new RoleQo();
        roleQo.setSize(8);
        roleQo.setPage(0);

        String json = roleFuture.findPage(roleQo.getPage(), roleQo.getSize(), roleQo.getName()).join();

        Gson gson = TreeMapConvert.getGson();
        TreeMap<String, Object> page = gson.fromJson(json, new TypeToken<TreeMap<String, Object>>() {
        }.getType());

        Pageable pageable = new PageRequest(roleQo.getPage(), roleQo.getSize(), null);

        List<RoleQo> list = new ArrayList<>();

        if (page.get("content") != null)
            list = gson.fromJson(page.get("content").toString(), new TypeToken<List<RoleQo>>() {
            }.getType());
        String count = page.get("totalelements").toString();

        Page<Map<String, Object>> newPage = new PageImpl(list, pageable, new Long(count));

        assert newPage.getTotalElements() > 0;
        for (RoleQo role : list) {
            log.info("=============== name={}", role.getName());
        }
        log.info("===============pageno={}, total_pages={}, total_elements={}", newPage.getNumber(),
                newPage.getTotalPages(), newPage.getTotalElements());
    }

    //@Test
    public void getUserPage() {
        UserQo userQo = new UserQo();
        userQo.setSize(8);
        userQo.setPage(0);

        String json = userFuture.findPage(userQo).join();

        Gson gson = TreeMapConvert.getGson();
        TreeMap<String, Object> page = gson.fromJson(json, new TypeToken<TreeMap<String, Object>>() {
        }.getType());

        Pageable pageable = new PageRequest(userQo.getPage(), userQo.getSize(), null);

        List<UserQo> list = new ArrayList<>();

        if (page.get("content") != null)
            list = gson.fromJson(page.get("content").toString(), new TypeToken<List<UserQo>>() {
            }.getType());
        String count = page.get("totalelements").toString();

        Page<Map<String, Object>> newPage = new PageImpl(list, pageable, new Long(count));

        assert newPage.getTotalElements() > 0;
        for (UserQo user : list) {
            log.info("=============== name={}", user.getName());
        }
        log.info("===============pageno={}, total_pages={}, total_elements={}", newPage.getNumber(),
                newPage.getTotalPages(), newPage.getTotalElements());
    }

    //@Test
    public void getKindPage() {
        KindQo kindQo = new KindQo();
        kindQo.setSize(8);
        kindQo.setPage(0);

        String json = kindFuture.findPage(kindQo.getPage(), kindQo.getSize(), kindQo.getName()).join();

        Gson gson = TreeMapConvert.getGson();
        TreeMap<String, Object> page = gson.fromJson(json, new TypeToken<TreeMap<String, Object>>() {
        }.getType());

        Pageable pageable = new PageRequest(kindQo.getPage(), kindQo.getSize(), null);

        List<KindQo> list = new ArrayList<>();

        if (page.get("content") != null)
            list = gson.fromJson(page.get("content").toString(), new TypeToken<List<KindQo>>() {
            }.getType());
        String count = page.get("totalelements").toString();

        Page<Map<String, Object>> newPage = new PageImpl(list, pageable, new Long(count));

        assert newPage.getTotalElements() > 0;
        for (KindQo kind : list) {
            log.info("=============== name={}", kind.getName());
        }
        log.info("===============pageno={}, total_pages={}, total_elements={}", newPage.getNumber(),
                newPage.getTotalPages(), newPage.getTotalElements());
    }
}
