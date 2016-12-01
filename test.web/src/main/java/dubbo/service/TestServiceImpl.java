package dubbo.service;

import java.util.ArrayList;
import java.util.List;

public class TestServiceImpl implements TestService{

    public List<String> getNameList() {
        List<String> names = new ArrayList<String>();
        names.add("hly");
        names.add("haha");
        return names;
    }
}
