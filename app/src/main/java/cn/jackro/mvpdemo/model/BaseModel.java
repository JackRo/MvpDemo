package cn.jackro.mvpdemo.model;

/**
 * BaseModel
 */
public class BaseModel {

    protected ApiService mApiService;

    public BaseModel() {
        Api.setBaseUrl("http://gank.io/api/");
        mApiService = Api.getInstance().getApiService();
    }

}
