package com.jaclon.mistakesOfBuz.productionready;

//import org.springframework.boot.actuate.health.CompositeHealthContributor;
//import org.springframework.boot.actuate.health.HealthContributor;
//import org.springframework.boot.actuate.health.NamedContributor;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
///**
// * CompositeHealthContributor 已经过期
// *
// * @author jaclon
// * @since 2021/8/12 14:51
// */
//@Component
//public class ThreadPoolsHealthContributor implements CompositeHealthContributor {
//
//    private Map<String, HealthContributor> contributors = new HashMap<>();
//
//    ThreadPoolsHealthContributor() {
//        this.contributors.put("demoThreadPool", new ThreadPoolHealthIndicator(ThreadPoolProvider.getDemoThreadPool()));
//        this.contributors.put("ioThreadPool", new ThreadPoolHealthIndicator(ThreadPoolProvider.getIOThreadPool()));
//    }
//
//    @Override
//    public HealthContributor getContributor(String name) {
//        return contributors.get(name);
//    }
//
//    @Override
//    public Iterator<NamedContributor<HealthContributor>> iterator() {
//        return contributors.entrySet().stream()
//                .map((entry) -> NamedContributor.of(entry.getKey(), entry.getValue())).iterator();
//    }
//}

public class ThreadPoolsHealthContributor{

}
