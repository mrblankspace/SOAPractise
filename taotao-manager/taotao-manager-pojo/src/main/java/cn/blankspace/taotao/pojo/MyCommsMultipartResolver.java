package cn.blankspace.taotao.pojo;

import org.springframework.web.multipart.commons.CommonsMultipartResolver;
public class MyCommsMultipartResolver extends CommonsMultipartResolver {
    private boolean resolveLazily = false;
    MyCommsMultipartResolver(){

    }
}
