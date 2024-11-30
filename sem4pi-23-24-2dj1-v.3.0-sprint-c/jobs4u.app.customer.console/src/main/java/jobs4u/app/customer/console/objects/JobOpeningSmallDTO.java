package jobs4u.app.customer.console.objects;

import com.google.common.primitives.Longs;
import lombok.Getter;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class JobOpeningSmallDTO {
    private final String jobReference;
    private final String position;
    private final long activeSince;
    private final long numApplicants;
    public JobOpeningSmallDTO(byte[] bytes){
        List<Byte> list = new ArrayList<>();
        int i = 0, j;
        while(bytes[i] != '0'){
            list.add(bytes[i++]);
        }
        i++;
        byte[] result = new byte[list.size()];
        for(int k = 0; k < list.size(); k++) result[k] = list.get(k);
        jobReference = new String(result);
        list = new ArrayList<>();
        while(bytes[i] != '0'){
            list.add(bytes[i++]);
        }
        i++;
        result = new byte[list.size()];
        for(int k = 0; k < list.size(); k++) result[k] = list.get(k);
        position = new String(result);
        result = new byte[8];
        for(j=0;j<result.length;j++){
            result[j] = bytes[i++];
        }
        activeSince = new BigInteger(result).longValue();
        result = new byte[8];
        for(j=0;j<result.length;j++){
            result[j] = bytes[i++];
        }
        numApplicants = new BigInteger(result).longValue();
    }
    public String toString(){
        StringBuilder active = new StringBuilder(String.valueOf(activeSince));
        //202404041530
        active.insert(4, "/"); active.insert(7, "/");
        active.insert(10, " "); active.insert(13, ":");
        //2024/04/04 15:30
        return String.format("Job Opening %s%n Position: %s%n Active since: %s%n Number of applicants: %d%n",
                jobReference, position, active, numApplicants);
    }
}
