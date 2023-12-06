package com.kingyqh.yangtzebi.manager;

import com.kingyqh.yangtzebi.common.ErrorCode;
import com.kingyqh.yangtzebi.exception.BusinessException;
import com.yupi.yucongming.dev.client.YuCongMingClient;
import com.yupi.yucongming.dev.common.BaseResponse;
import com.yupi.yucongming.dev.model.DevChatRequest;
import com.yupi.yucongming.dev.model.DevChatResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ai对话功能实现
 *
 */
@Service
public class AiManager {
    @Resource
    private YuCongMingClient yuCongMingClient;
    public String doChat(long modelId,String message){
//构造请求参数
        DevChatRequest devChatRequest = new DevChatRequest();
        devChatRequest.setModelId(modelId);
        devChatRequest.setMessage(message);
//获取响应结果
        BaseResponse<DevChatResponse> response = yuCongMingClient.doChat(devChatRequest);
        if(response == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"Ai 响应失败");
        }
        return response.getData().getContent();
    }
}