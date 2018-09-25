package cn.blankspace.taotao.service;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class BaseServiceImpl<T> implements  BaseService<T>{
    //Mapper没有了........

    private Class<T> clazz;

    public BaseServiceImpl(){
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType pType = (ParameterizedType) type;
        this.clazz = (Class<T>)pType.getActualTypeArguments()[0];
    }

    @Override
    public T queryById(Long id) {
        return null;

    }

    @Override
    public List<T> queryAll() {
        return null;
    }

    @Override
    public Integer queryCountByWhere(T t) {
        return null;
    }

    @Override
    public List<T> queryListByWhere(T t) {
        return null;
    }

    @Override
    public List<T> queryByPage(Integer page, Integer rows) {
        return null;
    }

    @Override
    public T queryOne(T t) {
        return null;
    }

    @Override
    public void save(T t) {

    }

    @Override
    public void saveSelective(T t) {

    }

    @Override
    public void updateById(T t) {

    }

    @Override
    public void updateByIdSelective(T t) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByIds(List<Object> ids) {

    }
}
