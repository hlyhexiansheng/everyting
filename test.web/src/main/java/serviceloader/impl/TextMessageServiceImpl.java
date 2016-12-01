package serviceloader.impl;

import serviceloader.MessageService;

/**
 * Created by Administrator on 2015/12/22.
 */
public class TextMessageServiceImpl implements MessageService {

    public String getMessage() {
        return "TextMessageService";
    }
}
