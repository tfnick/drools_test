package com.sample;

import org.kie.api.KieServices;
import org.kie.api.io.Resource;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.manager.*;

import java.util.HashMap;
import java.util.Map;

public class LoadResource {
    public static void main(String[] args) {
        //设置待加载的资源以及环境
        RuntimeEnvironmentBuilderFactory reb = RuntimeEnvironmentBuilder.Factory.get();
        RuntimeEnvironmentBuilder builder = reb.newEmptyBuilder();

        KieServices ks = KieServices.Factory.get();

        Resource bpmnResource = ks.getResources().newByteArrayResource(null, "utf-8");
        Resource drlResource = ks.getResources().newByteArrayResource(null, "utf-8");

        builder.addAsset(bpmnResource, ResourceType.BPMN2);
        builder.addAsset(drlResource, ResourceType.DRL);

        RuntimeEnvironment environment = builder.get();


        //创建RuntimeEngine  KieSession
        RuntimeManagerFactory rmf = RuntimeManagerFactory.Factory.get();
        RuntimeManager rm = rmf.newSingletonRuntimeManager(environment, "myruntime");

        RuntimeEngine re = rm.getRuntimeEngine(null);

        KieSession kis = re.getKieSession();

        //启动Process
        Map<String, Object> params = new HashMap<>();
        params.put("count", 1);
        kis.startProcess("test_process_id", params);


    }
}
