package cn.jackro.mvpdemo.model;

import cn.jackro.mvpdemo.util.RetrofitUtil;

/**
 * Api
 */
class Api {
    private ApiService mApiService;

    private Api() {
        RetrofitUtil.setBaseUrl("xxx");
        mApiService = RetrofitUtil.getInstance().getRetrofit().create(ApiService.class);
    }

    private static class Singleton {
        private static final Api INSTANCE = new Api();
    }

    static Api getInstance() {
        return Singleton.INSTANCE;
    }

    ApiService getApiService() {
        return mApiService;
    }
}
