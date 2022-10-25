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
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RespPermScope implements Serializable {
    @SerializedName("authDeptIds")
    @Expose
    public List<String> authDeptIds;
    @SerializedName("authUserIds")
    @Expose
    public List<String> authUserIds;
}