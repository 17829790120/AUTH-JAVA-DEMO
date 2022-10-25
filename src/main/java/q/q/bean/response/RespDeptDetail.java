package q.q.bean.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespDeptDetail implements Serializable {
    @SerializedName("thirdDepId")
    @Expose
    public String thirdDepId;
    @SerializedName("parentDepId")
    @Expose
    public String parentDepId;
    @SerializedName("depName")
    @Expose
    public String depName;
    @SerializedName("order")
    @Expose
    public Integer order;
}
