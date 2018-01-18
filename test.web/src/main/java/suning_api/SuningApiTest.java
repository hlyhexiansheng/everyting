package suning_api;

import com.suning.api.DefaultSuningClient;
import com.suning.api.entity.custom.CulitemdetailQueryRequest;
import com.suning.api.entity.custom.CulitemdetailQueryResponse;
import com.suning.api.exception.SuningApiException;

/**
 * Created by noodles on 2017/11/30 15:25.
 */
public class SuningApiTest {
    public static void main(String[] args) {
        CulitemdetailQueryRequest request = new CulitemdetailQueryRequest();
        request.setItemCode("111");
        request.setProductCode("1000000001");
//api入参校验逻辑开关，当测试稳定之后建议设置为 false 或者删除该行
        request.setCheckParam(true);
        String serverUrl = "https://open.suning.com/api/http/sopRequest";
        String appKey = "你的appKey";
        String appSecret = "你的appSecret";
        DefaultSuningClient client = new DefaultSuningClient(serverUrl, appKey, appSecret, "json");
        try {
            CulitemdetailQueryResponse response = client.excute(request);
            System.out.println("返回json/xml格式数据 :" + response.getBody());
        } catch (SuningApiException e) {
            e.printStackTrace();
        }
    }
}
