package youthshelter.youth.co.kr.Util;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitService {

    @GET("30/")
    Call<Response_Culture_Info.Example> GetInfo();

}
