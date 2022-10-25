package q.q.bean.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReqUserAndDeptParam implements Serializable {

    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("thirdDepId")
    @Expose
    private String thirdDepId;
    @SerializedName("thirdDepIdList")
    @Expose
    public List<String> thirdDepIdList;
    @SerializedName("fetch")
    @Expose
    public boolean fetch;
    @SerializedName("thirdUserIdList")
    @Expose
    public List<String> thirdUserIdList;
}
