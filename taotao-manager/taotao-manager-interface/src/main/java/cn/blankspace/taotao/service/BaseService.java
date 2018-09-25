package cn.blankspace.taotao.service;

import java.util.List;

public interface BaseService<T>{

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public T queryById(Long id);

    /**
     * 查询所有数据
     * @return
     */
    public List<T> queryAll();

    /**
     * 根据条件查询数据条数
     * @param t
     * @return
     */
    public Integer queryCountByWhere(T t);

    /**
     * 根据条件查询数据
     * @param t
     * @return
     */
    public List<T> queryListByWhere(T t);

    /**
     * 分页查询数据
     * @return
     */
    public List<T>queryByPage(Integer page, Integer rows);

    /**
     * 根据条件查询一条数据
     * @return
     */
    public T queryOne(T t);

    /**
     * 新增
     * @param t
     */
    public void save(T t);

    /**
     * 新增，忽略空参数
     * @param t
     */
    public void saveSelective(T t);

    /**
     * 根据主键更新
     */
    public void updateById(T t);

    /**
     * 根据主键更新忽略空参数
     * @param t
     */
    public void updateByIdSelective(T t);

    /**
     * 根据id删除数据
     */
    public void deleteById(Long id);

    /**
     * 根据id批量删除数据
     * @param ids
     */
    public void deleteByIds(List<Object> ids);


}
