package jobs4u.core.jobapplication.dto;

import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import jobs4u.core.DTO;
import jobs4u.core.jobapplication.domain.ApplicationState;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class JobApplicationSmallDTO implements DTO {
    private final int id;
    private final String state;
    private final long numApplicants;
    public JobApplicationSmallDTO(int id, ApplicationState state, long numApplicants){
        this.id = id;
        this.state = state.toString()+'0';
        this.numApplicants = numApplicants;
    }
    @Override
    public byte[] toBytes() {
        List<Byte> list = new ArrayList<>();
        for(byte b : Ints.toByteArray(id)) list.add(b);
        for(byte b : state.getBytes(StandardCharsets.US_ASCII)) list.add(b);
        for(byte b : Longs.toByteArray(numApplicants)) list.add(b);
        byte[] array = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) array[i] = list.get(i);
        return array;
    }
}