package cn.jackro.mvpdemo.bean;

import java.util.List;

/**
 * @author JackRo
 */
public class ApiResult<T> {

    public boolean error;

    public List<T> results;
}
