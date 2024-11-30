package jobs4u.core.jobopening.dto;

import com.google.common.primitives.Longs;
import jobs4u.core.DTO;
import lombok.Getter;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class JobOpeningSmallDTO implements DTO {
    private final String jobReference;
    private final String position;
    private final long activeSince;
    private final long numApplicants;
    public JobOpeningSmallDTO(String jobReference, String position, LocalDateTime activeSince, long numApplicants){
        this.jobReference = jobReference;
        this.position = position;
        String active = String.format("%04d%02d%02d%02d%02d", activeSince.getYear(), activeSince.getMonthValue(),
                activeSince.getDayOfMonth(), activeSince.getHour(), activeSince.getMinute());
        this.activeSince = Long.parseLong(active);
        this.numApplicants = numApplicants;
    }
    @Override
    public byte[] toBytes() {
        List<Byte> list = new ArrayList<>();
        for(byte b : jobReference.getBytes(StandardCharsets.US_ASCII)) list.add(b);
        for(byte b : position.getBytes(StandardCharsets.US_ASCII)) list.add(b);
        for(byte b : Longs.toByteArray(activeSince)) list.add(b);
        for(byte b : Longs.toByteArray(numApplicants)) list.add(b);
        byte[] array = new byte[list.size()];
        for(int i = 0; i < list.size(); i++) array[i] = list.get(i);
        return array;
    }
}
