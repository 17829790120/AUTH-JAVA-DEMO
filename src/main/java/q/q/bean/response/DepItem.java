package q.q.bean.response;

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
public class DepItem implements Serializable {

    private static final long serialVersionUID = 6345656440596726579L;
    @SerializedName("depId")
    private int depId;

    @SerializedName("depPath")
    private String depPath;

    @SerializedName("depName")
    private String depName;
}