package bind.kripton1110.model.stage1;

import java.util.List;

import com.abubusoft.kripton.annotation.Bind;
import com.abubusoft.kripton.annotation.BindType;

@BindType
public class Response {

    public List<User> users;

    public String status;

    @Bind("is_real_json")
    public boolean isRealJson;
}