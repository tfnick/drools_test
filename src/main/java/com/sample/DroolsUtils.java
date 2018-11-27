package com.sample;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class DroolsUtils {

    private Logger logger = LoggerFactory.getLogger(DroolsUtils.class);

    //KnowledgeBase 缓存(key：场景标识)
    private static Map<String, KieBase> ruleMap = new ConcurrentHashMap<>();

    private DroolsUtils() {
    }

    private static class SingletonHolder {
        static DroolsUtils instance = new DroolsUtils();
    }

    public static DroolsUtils getInstance() {
        return SingletonHolder.instance;
    }

    /**
     *
     * @param ruleKey
     * @return
     */
    public KieSession getDrlSession(final String ruleKey){
        KieBase kBase = ruleMap.get(ruleKey);
        if (kBase == null) {
            logger.error("找不到规则{}对应的KieBase", ruleKey);
            return null;
        }
        KieSession kieSession = kBase.newKieSession();
        kieSession.addEventListener(new GrAgendaEventListener());
        return kieSession;
    }

    /**
     *
     * @param ruleKey
     * @param content
     */
    public void initDrlSession(final String ruleKey, final String content) {
        try {
            // 设置时间格式
            System.setProperty("drools.dateformat", "yyyy-MM-dd");
            //为防止规则文件名字重复，此处加上时间戳( 格式：场景标识+时间戳+.drl)
            //String ruleFileName = ruleKey + "_" + System.currentTimeMillis() + ".drl";
            String ruleFileName = ruleKey + ".drl";

            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kfs = kieServices.newKieFileSystem();
            kfs.write("src/main/resources/com/gr/ruleeengine/suite/rule/" + ruleFileName, content.getBytes("UTF-8"));
            KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
            if (!kieBuilder.getResults().getMessages(Message.Level.ERROR).isEmpty()) {
                logger.error("规则引擎编译错误:{}", kieBuilder.getResults().getMessages());
                throw new RuntimeException("规则引擎编译错误:" + kieBuilder.getResults().getMessages());
            }
            KieContainer kieContainer = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId());
            KieBase kBase = kieContainer.getKieBase();
            //放入缓存
            ruleMap.put(ruleKey, kBase);
        } catch (Exception e) {
            logger.error("规则引擎初始化失败，请查看错误信息:{}", e.getMessage());
            throw new RuntimeException("规则引擎初始化失败，请查看错误信息:" + e.getMessage());
        }

    }


    /**
     *
     * @param rule
     * @return
     */
    public Boolean compileRule(final String rule) {

        //为防止规则文件名字重复，此处加上时间戳( 格式：场景标识+时间戳+.drl)
        String ruleFileName = "testRule" + System.currentTimeMillis() + "testRule.drl";

        try {
            KieServices kieServices = KieServices.Factory.get();
            KieFileSystem kfs = kieServices.newKieFileSystem();
            kfs.write("src/main/resources/com/drools/rules/" + ruleFileName, rule.getBytes("UTF-8"));
            KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
            if (kieBuilder.getResults().getMessages(Message.Level.ERROR).isEmpty()) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            logger.error("获取 KieBase 信息错误:{}", e);
            throw new RuntimeException("获取 KieBase 信息错误");
        }

        return Boolean.FALSE;
    }


    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 移除对应的规则（供其它部分调用，比如规则的修改和删除）
     *
     * @param key 场景标识
     */
    public static void removeRuleMap(final String key) {
        ruleMap.remove(key);
    }

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 清空规则缓存
     */
    public static void clearRuleMap() {
        ruleMap.clear();
    }
}

