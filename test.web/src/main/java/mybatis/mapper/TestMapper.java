package mybatis.mapper;

import mybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.Map;

/**
 * Created by Administrator on 2015/12/7.
 */

public interface TestMapper {

    int selectCount();
//
//    int insertUser(User user);
//
//    Map<String,String> getUser(int userId);
//
//    int updateUser(User user);

    @Select("SELECT * FROM USER where id = #{userID}")
    User getUserFromAnnotation(@Param("userID")int userId);
}
